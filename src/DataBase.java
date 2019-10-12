/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author thoma
 */
public class DataBase
{
    int nr_of_nodes;
    int max_capacity;
    ArrayList<Nod> Noduri = new ArrayList<Nod>();

    public void CREATEDB(DataBase DB, int nr_de_noduri, int capacitate_maxima)
        {
            DB.nr_of_nodes = nr_de_noduri;
            DB.max_capacity = capacitate_maxima;
            for (int i = 0; i < DB.nr_of_nodes; i++)
            {
                Nod nodulet = new Nod();
                nodulet.max_capacity = DB.max_capacity;
                DB.Noduri.add(nodulet);
            }
        }

    public void SNAPSHOTDB(DataBase DB, PrintWriter pw)
        {
            ArrayList<Nod> Nodurile = DB.Noduri;
            int pozitie = 0;
            int am_printat_ceva = 0;
            for (Nod i : Nodurile)
            {
                if (i.Entitati.size() > 0)
                {
                    int exista = 0;
                    for (Entitate j : i.Entitati)
                    {
                        if (j.Instante.size() > 0)
                        {
                            exista = 1;
                            break;
                        }
                    }
                    if (exista == 1)
                    {
                    pozitie++;
                    pw.print("Nod" + pozitie);
                    pw.print("\n");
                    pozitie--;
                    }
                }

                for (Instanta_si_Atribut_de_Printat j : i.Instante_de_Printat)
                {
                    am_printat_ceva = 1;
                    pw.print(j.nume + " ");
                    /// aici trebuie sa printez instante si atribute
                    /// la modul Id:1 Nume:Apa Pret:15
                    /// ceea ce inseamna Atribut : val de la instanta
                    for (int it = 0; it < j.Atribute.size(); it++)
                    {
                        pw.print(j.Atribute.get(it).nume + ":");
                        if (j.Atribute.get(it).type == 0)
                        {
                            if (it == j.instance.size()-1)
                               pw.print(j.instance.get(it).x);
                            else
                               pw.print(j.instance.get(it).x + " ");
                        }
                        else
                        if (j.Atribute.get(it).type == 1)
                        {
                            if (it == j.instance.size()-1)
                               pw.print(j.instance.get(it).y);
                            else
                               pw.print(j.instance.get(it).y + " ");
                        }
                        else
                        if (j.Atribute.get(it).type == 2)
                        {
                            int val = j.instance.get(it).z.intValue();

                            if (it == j.instance.size()-1)
                            {
                                if (j.instance.get(it).z == val)
                                    pw.print(val);
                                else
                                    pw.print(j.instance.get(it).z);
                            }
                            else
                            {
                                if (j.instance.get(it).z == val)
                                    pw.print(val + " ");
                                else
                                    pw.print(j.instance.get(it).z + " ");
                            }
                        }
                    }
                    pw.print("\n");
                }

                pozitie++;
            }
            if (am_printat_ceva == 0)
            {
                pw.print("EMPTY DB");
                pw.print("\n");
            }
        }
}
