/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author thoma
 */
public class Tema2
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        Entitate entPTmetode = new Entitate();
        File file = new File(args[0]+"_out");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(file);
        FileReader file1 = new FileReader(args[0]);
        BufferedReader reader = new BufferedReader(file1);

        int nr1 = 0; int nr2 = 0; int cnt = 0;
        DataBase DB  = new DataBase();
        String line = reader.readLine();
        String comanda = new String();
        String nume = new String();
        String numar1 = new String();
        String numar2 = new String();

        for (String val: line.split(" ", 4))
            {
                if (cnt == 0)
                    comanda = val;
                else if (cnt == 1)
                    nume = val;
                else if (cnt == 2)
                    numar1 = val;
                else if (cnt == 3)
                    numar2 = val;
                cnt++;
            }

        nr1 = Integer.parseInt(numar1);
        nr2 = Integer.parseInt(numar2);

        DB.CREATEDB(DB, nr1, nr2);

        while (true)
    {
        char caracter;
        int val_car = reader.read();
        if (val_car == -1)
            break;
        else
            caracter = (char) val_car;

        switch (caracter)
        {
            case 'C':
                for (int i = 0; i < 6; i++)
                    caracter = (char) reader.read();

                Entitate ent = new Entitate();
                line = reader.readLine();
                ent.Creez_Entitate(ent, line, DB);
                break;
            case 'I':
                for (int i = 0; i < 6; i++)
                    caracter = (char) reader.read();
                line = reader.readLine();
                entPTmetode.Inserare_Instanta(line, DB);
                break;
            case 'S':
                DB.SNAPSHOTDB(DB, pw);
                line = reader.readLine();
                break;
            case 'U':
                for (int i = 0; i < 6; i++)
                    caracter = (char) reader.read();
                line = reader.readLine();
                entPTmetode.Update_Instanta(line, DB);
                break;
            case 'D':
                for (int i = 0; i < 6; i++)
                    caracter = (char) reader.read();
                line = reader.readLine();
                entPTmetode.Sterge_Instanta(line, DB, pw);
                break;
            default:
                break;
        }
    }
        pw.close();
    }
}
