import {Injectable} from '@angular/core';
import {Workbook} from 'exceljs/dist/exceljs.min.js';
import * as fs from 'file-saver';
import {DatePipe} from "@angular/common";


@Injectable({
  providedIn: 'root'
})
export class ExportExcelService {

  constructor(private datePipe: DatePipe) {
  }

  exportExcel(excelData: { title: any; data: any }) {
    //Title, Header & Data
    const title = excelData.title;
    // const header = excelData.headers;
    const data = excelData.data;

    //Create a workbook with a worksheet
    let workbook = new Workbook();
    let worksheet = workbook.addWorksheet('Contracts Data');

    //Add Row and formatting
    worksheet.mergeCells('D1', 'L4');
    let titleRow = worksheet.getCell('D1');
    titleRow.value = title;
    titleRow.font = {
      name: 'Comic Sans MS', family: 4, size: 16, underline: 'double', bold: true
    };
    titleRow.alignment = {vertical: 'middle', horizontal: 'center'};
    console.log(titleRow)
    worksheet.addRow([]);
    // Date
    let subTitleRow = worksheet.addRow(['Date : ' + this.datePipe.transform(new Date(), 'medium')])

    //Blank Row
    worksheet.addRow([]);

    //Adding Header Row
    // let headerRow = worksheet.addRow(header);
    worksheet.views = [{}]
    let headerRow = Object.keys(data[0]);

    let columnHeader = [];
    let changeNameHeader = [];
    // headerRow.forEach(key => {
    //   if (key == 'pawnItem') {
    //     columnHeader.push({key, width: 27})
    //   } else if (key == 'employee' || key == 'liquidationPrice' || key == 'customer' || key == 'itemPrice' || key == 'profit') {
    //     columnHeader.push({key, width: 20})
    //   } else if (key == 'interestRate' || key == 'startDate' || key == 'returnDate' ||
    //     key == 'code' || key == 'endDate') {
    //     columnHeader.push({key, width: 15})
    //   } else {
    //     columnHeader.push({key, width: 10})
    //   }
    // });

    headerRow.forEach(key => {
      switch (key) {
        case 'pawnItem':
          changeNameHeader.push('Đồ cầm') && columnHeader.push({key, width: 27})
          break;
        case 'employee':
          changeNameHeader.push('Nhân viên') && columnHeader.push({key, width: 20})
          break;
        case 'liquidationPrice':
          changeNameHeader.push('Tiền thanh lý') && columnHeader.push({key, width: 20})
          break;
        case 'customer':
          changeNameHeader.push('Khách hàng') && columnHeader.push({key, width: 20})
          break;
        case 'itemPrice':
          changeNameHeader.push('Tiền cầm đồ') && columnHeader.push({key, width: 20})
          break;
        case 'profit':
          changeNameHeader.push('Lợi nhuận') && columnHeader.push({key, width: 20})
          break;
        case 'interestRate':
          changeNameHeader.push('Lãi suất') && columnHeader.push({key, width: 15})
          break;
        case 'startDate':
          changeNameHeader.push('Ngày bắt đầu') && columnHeader.push({key, width: 15})
          break;
        case 'returnDate':
          changeNameHeader.push('Ngày trả đồ') && columnHeader.push({key, width: 15})
          break;
        case 'endDate':
          changeNameHeader.push('Ngày kết thúc') && columnHeader.push({key, width: 15})
          break;
        case 'code':
          changeNameHeader.push('Mã hợp đồng') && columnHeader.push({key, width: 15})
          break;
        case 'id':
          changeNameHeader.push('Id') && columnHeader.push({key, width: 10})
          break;
        case 'type':
          changeNameHeader.push('Kiểu') && columnHeader.push({key, width: 10})
          break;
        case 'status':
          changeNameHeader.push('Trạng thái') && columnHeader.push({key, width: 10})
          break;
      }
    });
    console.log(columnHeader);
    console.log(changeNameHeader);
    worksheet.columns = columnHeader;

    console.log(headerRow);
    const header = {
      money: false,
      fill: {
        type: 'pattern',
        pattern: 'solid',
        fgColor: {argb: 'ff3300'},
        bgColor: {argb: 'ff3300'}
      },
      border: true,
      font: {
        bold: true,
        color: {argb: 'FFFFFF'},
        size: 12,
      },
      alignment: {vertical: 'middle', horizontal: 'center'},
    }

    addRows(worksheet, changeNameHeader, header);

    // Adding Data with Conditional Formatting
    data.forEach((d: any) => {
      let row = worksheet.addRow(Object.values(d));

      row.eachCell({includeEmpty: true}, (cell) => {
        console.log( cell.value);
        if (typeof cell.value === 'number') {
          if (cell.value > 1000) {
            cell.numFmt = '$#,##0';
          } else if (cell.value < 1 && cell.value > 0) {
            cell.numFmt = '#,#0.00"%"';
          }
        }
        if(typeof cell.value === 'boolean'){
          if(cell.value === false){
            cell.value = 'Cầm đồ'
          }else {
            cell.value = 'Thanh lý'
          }
        }
      });
      let sales = row.getCell(14);
      let color = 'FF99FF99';
      let sales_val = sales.value || 0;
      // Conditional fill color
      if (sales_val < 200000) {
        color = 'FF9999';
      }

      sales.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: {argb: color},
      };
      for (let j = 1; j <= 14; j++) {
        row.getCell(j).border = {
          top: {style: 'thin'},
          left: {style: 'thin'},
          bottom: {style: 'thin'},
          right: {style: 'thin'}
        };
      }
      row.alignment = {vertical: 'middle', horizontal: 'center'};

    });
    worksheet.addRow([]);
    worksheet.addRow([]);

    //Footer Row
    const footerRow = worksheet.addRow([
      'Bảng thống kê hợp đồng từ tiệm cầm đồ số 1 VN',
    ]);
    footerRow.getCell(1).fill = {
      type: 'pattern',
      pattern: 'solid',
      fgColor: {argb: 'ff3300'}
    };
    footerRow.font = {
      bold: true,
      color: {argb: 'FFFFFF'},
      size: 12,
    };
    footerRow.alignment = {vertical: 'middle', horizontal: 'center'};
    //Merge Cells

    footerRow.getCell(5).border = {
      top: {style: 'thin'},
      left: {style: 'thin'},
      bottom: {style: 'thin'},
      right: {style: 'thin'}
    };

// Merge Cells
    worksheet.mergeCells(`A${footerRow.number}:N${footerRow.number}`);

    //Generate & Save Excel File
    workbook.xlsx.writeBuffer().then((data) => {
      let blob = new Blob([data], {
        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
      });
      fs.saveAs(blob, title + '.xlsx');
    });
  }

}

function addRows(workSheet, data, section) {
  const borderStyles = {
    top: {style: 'thin'}, left: {style: 'thin'}, bottom: {style: 'thin'}, right: {style: 'thin'},
  }
  const row = workSheet.addRow(data);
  row.eachCell({includeEmpty: true}, (cell, colNumber) => {
    if (section?.border) {
      cell.border = borderStyles;
    }
    if (section?.money && typeof cell.value === 'number') {
      cell.alignment = {horizontal: 'right', vertical: 'middle'};
      cell.numFmt = '$#,##0.00;[Red]-$#0.00';
    }
    if (section?.alignment) {
      cell.alignment = section.alignment;
    } else {
      cell.alignment = {vertical: 'middle'};
    }
    if (section?.font) {
      cell.font = section.font;
    }
    if (section?.fill) {
      cell.fill = section.fill;
    }
  });
  return row;

}
