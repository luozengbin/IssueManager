/*
 * Copyright 2011 Zouhin Ro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package magicware.scm.redmine.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import magicware.scm.redmine.tools.config.Config;
import magicware.scm.redmine.tools.config.ConfigFacade;
import magicware.scm.redmine.tools.config.SyncItem;
import magicware.scm.redmine.tools.model.Issue;
import magicware.scm.redmine.tools.util.ExcelUtils;
import magicware.scm.redmine.tools.util.FileUtils;
import net.arnx.jsonic.JSON;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 */
public class IssueSyncApp {

    protected static Logger log = LoggerFactory.getLogger(IssueSyncApp.class);

    private RedmineClient redmineClient = null;

    public IssueSyncApp() {
        super();

        Config config = ConfigFacade.getConfig();

        redmineClient = new RedmineClient(config.getRedmineHost(),
                config.getRedminePort(), config.getRedmineContext());

        redmineClient.fillBasicAuth(config.getRedmineAuthUser(),
                config.getRedmineAuthPwd());
    }

    public void execute(SyncItem syncItem) throws IOException,
            InvalidFormatException {

        FileInputStream in = null;

        try {

            // チケット登録JSONテンプレートの読み込む
            String issueTemplate = FileUtils.readFileAsString(syncItem
                    .getJsonTemplate());

            // 書換えフィールドの検索
            Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP)
                    .matcher(issueTemplate);

            List<MatchResult> mrList = new ArrayList<MatchResult>();

            while (m.find()) {
                MatchResult mr = m.toMatchResult();
                mrList.add(mr);
            }

            // 外部チケット管理エクセル台帳を読み込む
            in = new FileInputStream(syncItem.getFilePath());
            Workbook wb = WorkbookFactory.create(in);

            FormulaEvaluator evaluator = wb.getCreationHelper()
                    .createFormulaEvaluator();

            Sheet sheet = wb.getSheet(syncItem.getSheetName());
            Row row = null;
            Cell cell = null;

            List<String> issues = new ArrayList<String>();

            // エクセルの後から新規チケットを探し
            for (int i = sheet.getLastRowNum(); i >= (syncItem
                    .getKeyRowBeginIdx() > 0 ? (syncItem.getKeyRowBeginIdx() - 1)
                    : 0); i--) {
                // チケット管理番号の抽出
                row = sheet.getRow(i);

                if (row != null) {

                    String keyNo = ExcelUtils.getCellContent(
                            row.getCell(syncItem.getKeyColumnIdx() - 1),
                            evaluator);

                    // 行番号のない行が見当たると処理とめる
                    if (StringUtils.isBlank(keyNo)) {
                        break;
                    }

                    // 未登録の場合、登録を行う
                    if (redmineClient.queryIssue(syncItem.getProjectId(),
                            syncItem.getKeyFiledId(), keyNo) == 0) {
                        StringBuilder newIssue = new StringBuilder();
                        int eolIdx = 0;
                        for (MatchResult matchResult : mrList) {

                            newIssue.append(issueTemplate.substring(eolIdx,
                                    matchResult.start()));

                            int cellIndex = Integer.valueOf(matchResult
                                    .group(1)) - 1;
                            cell = row.getCell(cellIndex);
                            String cellvalue = ExcelUtils.getCellContent(cell,
                                    evaluator);

                            // 登録値の代入
                            String valueMapStr = matchResult.group(3);
                            Map<String, String> valueMap = null;
                            if (valueMapStr != null) {
                                valueMap = JSON.decode(valueMapStr);
                                if (StringUtils.isNotEmpty(cellvalue)
                                        && valueMap.containsKey(cellvalue)) {
                                    cellvalue = valueMap.get(cellvalue);
                                } else {
                                    cellvalue = valueMap.get("default");
                                }
                            }

                            if (StringUtils.isNotEmpty(cellvalue)) {
                                cellvalue = StringEscapeUtils
                                        .escapeJavaScript(cellvalue);
                                newIssue.append(cellvalue);
                            }
                            eolIdx = matchResult.end();
                        }
                        newIssue.append(issueTemplate.substring(eolIdx));
                        issues.add(newIssue.toString());
                    } else {
                        // エクセル逆なめを止める
                        break;
                    }
                }
            }

            for (int i = issues.size() - 1; i >= 0; i--) {
                Map<String, Issue> issueMap = JSON.decode(issues.get(i));
                log.debug("create new issue >>>");
                log.debug(JSON.encode(issueMap, true));
                redmineClient.createNewIssue(issues.get(i));
            }

        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.redmineClient.shutdown();
    }
}
