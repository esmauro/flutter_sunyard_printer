package com.freelogic.flutter_sunyard_printer;

import android.content.Context;
import android.util.Log;

// lineas del emulador
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;
import com.socsi.smartposapi.printer.FontType;
import com.socsi.smartposapi.printer.PrintRespCode;
import com.socsi.smartposapi.printer.Printer2;
import com.socsi.smartposapi.printer.TextEntity;
import com.socsi.utils.HexUtil;
import com.socsi.utils.StringUtil;

import com.socsi.smartposapi.DeviceMaster;
// lineas del emulador

import com.freelogic.flutter_sunyard_printer.utils.Base64Utils;
import com.freelogic.flutter_sunyard_printer.utils.BitmapUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FlutterSunyardPrinterModule {

  PrintRespCode check;
  Bitmap LineDivider;
  Context context;
  private FontType mCh = FontType.SIMSUM, mEn = FontType.SIMSUM;
  int i = 0 ;

  Printer2 print = Printer2.getInstance();

  public void initAidl(Context context) {
    this.context = context;
    DeviceMaster.getInstance().init(context);
  }

  public void feedPaper(int lines) {
    print.appendTakePaper(Printer2.FEEDING_PAPER, lines);
  }

  public void printFixedImage(String base64) {

      byte[] bytes = Base64Utils.decode(base64);
      for (int i = 0; i < bytes.length; ++i) {
        // ajust data
        if (bytes[i] < 0) {
          bytes[i] += 256;
        }
      }
      print.appendImage(BitmapUtil.convertToThumb(bytes, 280), Align.CENTER);

      feedPaper(5);
      PrintRespCode printRespCode = print.startPrint();

      if (printRespCode != PrintRespCode.Print_Success) {
          if (printRespCode == PrintRespCode.Printer_PaperLack || printRespCode == PrintRespCode.print_Unknow) {
              Toast.makeText(context, "Printer is out of paper", Toast.LENGTH_SHORT).show();
          }
          else {
            Toast.makeText(context, "Print failed", Toast.LENGTH_SHORT).show();
          }
      }
  }
/*
  public void print(List<String> lines, PrinterListener callback) {
      final Printer2 printer = Printer2.getInstance(context);

      for (int i = 0; i < lines.size(); i++) {
          final String line = lines.get(i) + "\n";
          printer.appendTextEntity2(new TextEntity(line, FontLattice.EIGHTEEN, false, null));
      }

      feedPaper(6);

      PrintRespCode printRespCode = printer.startPrint();
      if (callback != null) {
          if (printRespCode == PrintRespCode.Print_Success) {
              callback.onSuccess();
          } else {
              int status;
              if (printRespCode == PrintRespCode.Printer_PaperLack) {
                  status = CardReaderStatus.PRINT_NO_PAPER;
              } else if (printRespCode == PrintRespCode.Printer_Busy) {
                  status = CardReaderStatus.PRINT_BUSY;
              } else {
                  status = CardReaderStatus.PRINT_ERROR;
              }

              callback.onError(status, printRespCode.toString());
          }
      }
  }
*/






  public void printFixedText() {
    print.appendTextEntity2(new TextEntity("Fin de la prueba.", null, false, null));  
    
    PrintRespCode printRespCode = print.startPrint();
    Log.d("mtest", "yendo a imprimir");
    Toast.makeText(this.context, "Luego de ejecutar", Toast.LENGTH_SHORT).show();
//
    if (printRespCode != PrintRespCode.Print_Success) {
        if (printRespCode == PrintRespCode.Printer_PaperLack || printRespCode == PrintRespCode.print_Unknow) {
            Toast.makeText(this.context, "Printer is out of paper", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this.context, "Print failed", Toast.LENGTH_SHORT).show();
    }
    Log.i("PrintActivity", printRespCode.toString().toString());    
  }

  public void printLine(){
    appendTextEntity("-------------------------------");
  }

  private int appendTextEntity(String str) {
      int i = Printer2.getInstance().appendTextEntity2(new TextEntity(str, mCh, mEn, FontLattice.TWENTY_FOUR, false, Align.LEFT,true));
      return i;
  }

  private int appendTextEntityFontSixteen(String str) {
      int i = Printer2.getInstance().appendTextEntity2(new TextEntity(str,mCh, mEn, FontLattice.SIXTEEN, false, Align.LEFT,true));
      return i;
  }

}
