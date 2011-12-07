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
package magicware.scm.redmine.tools.util;

import magicware.scm.redmine.tools.Constants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

public class ExcelUtils {

    public static String getCellContent(Cell cell, FormulaEvaluator evaluator) {

        String result = null;

        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            result = cell.getRichStringCellValue().getString();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                result = Constants.DATE_FORMAT.format(cell.getDateCellValue());
            } else {
                result = String.valueOf(Double.valueOf(
                        cell.getNumericCellValue()).intValue());
            }
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            result = String.valueOf(cell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_FORMULA:
            switch (evaluator.evaluateFormulaCell(cell)) {
            case Cell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = Constants.DATE_FORMAT.format(cell
                            .getDateCellValue());
                } else {
                    result = String.valueOf(Double.valueOf(
                            cell.getNumericCellValue()).intValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                result = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_ERROR:
                result = String.valueOf(cell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                break;
            }
            break;
        default:
            break;
        }
        return result;
    }
}
