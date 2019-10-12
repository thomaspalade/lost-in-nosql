/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thoma
 */
public class Entitate
{
        String nume;
	int RF;
	int nr_of_atributes;
        ArrayList<Atribut> Atribute = new ArrayList<Atribut>(); /// vectorul de atribute gen si de chestii ai sa vezi
        ArrayList<Instanta> Instante = new ArrayList<Instanta>();

        public void Creez_Entitate(Entitate ent, String line, DataBase DB)
        {
            String[] parts = line.split(" ");

            int el_curent = 3;
            ent.nume = parts[0];
            ent.RF = Integer.parseInt(parts[1]);
            ent.nr_of_atributes = Integer.parseInt(parts[2]);
            /// Scanner scan = new Scanner(System.in);

            for (int i = 0; i < ent.nr_of_atributes; i++)
            {
                /// Id Integer Nume String Pret Float
                String nume_atribut = parts[el_curent++];
                String tip_atribut = parts[el_curent++];

                Atribut obiect = new Atribut();
                obiect.nume = nume_atribut;
                if (tip_atribut.equals("Integer"))
                    obiect.type = 0;
                else if (tip_atribut.equals("String"))
                    obiect.type = 1;
                else if (tip_atribut.equals("Float"))
                    obiect.type = 2;

                ent.Atribute.add(obiect);
            }

            int cnt = 0; /// cnt numara in cate noduri am pus entitate already

            for (Nod i : DB.Noduri)
            {
                if (i.Instante_de_Printat.size() < i.max_capacity)
                {
                    /// am adaugat entitatea pe un nou nod
                    /// dar de fapt ar trebui sa arate i.Entitati.add(ent1)
                    Entitate entClone = new Entitate();
                    entClone.RF = ent.RF;
                    entClone.nr_of_atributes = ent.nr_of_atributes;
                    entClone.nume = ent.nume;
                    ArrayList<Atribut> AtributeClone = new ArrayList<Atribut>();
                    ArrayList<Instanta> InstanteClone = new ArrayList<Instanta>();
                    for (Atribut j :  ent.Atribute)
                    {
                        Atribut jClona = new Atribut();
                        jClona.nume = j.nume;
                        jClona.type = j.type;

                        AtributeClone.add(jClona);
                    }
                    for (Instanta j : ent.Instante)
                    {
                        Instanta jClona = new Instanta();
                        for (Val_Atribut k : j.Val_Atribute)
                        {
                            Val_Atribut kClona = new Val_Atribut();
                            kClona.type = k.type;
                            kClona.x = k.x;
                            kClona.y = k.y;
                            kClona.z = k.z;
                            jClona.Val_Atribute.add(kClona);
                        }
                    }
                    entClone.Atribute = AtributeClone;
                    entClone.Instante = InstanteClone;
                    i.Entitati.add(0, entClone);

                }
            }
        }

        public void Inserare_Instanta(String line, DataBase DB)
        {
            /// INSERT Produs 1 Apa 5.2
            /// in primul rand parcurg Vectorii de noduri si vad pe unde gasesc entitati cu numele asta
            /// Scanner scan = new Scanner(System.in);
            String[] parts = line.split(" ");
            int el_curent = 1;

            int found = 0;
            Instanta Noua_Instanta = new Instanta();

            for (Nod i : DB.Noduri)
            {
                if (i.Instante_de_Printat.size() < i.max_capacity)
                for (Entitate j : i.Entitati)
                {
                    if (j.nume.equals(parts[0]))
                    {
                        if (found == 0)
                        {
                            found = 1;
                            for (int k = 0; k < j.Atribute.size(); k++)
                            {
                                if (j.Atribute.get(k).type == 0)
                                {
                                    Val_Atribut obiect = new Val_Atribut();
                                    obiect.type = 0;
                                    obiect.x = Integer.parseInt(parts[el_curent++]);
                                    Noua_Instanta.Val_Atribute.add(obiect);
                                }
                                else
                                if (j.Atribute.get(k).type == 1)
                                {
                                    Val_Atribut obiect = new Val_Atribut();
                                    obiect.type = 1;
                                    obiect.y = parts[el_curent++];
                                    Noua_Instanta.Val_Atribute.add(obiect);
                                }
                                else
                                if (j.Atribute.get(k).type == 2)
                                {
                                    Val_Atribut obiect = new Val_Atribut();
                                    obiect.type = 2;
                                    obiect.z = Float.parseFloat(parts[el_curent++]);
                                    Noua_Instanta.Val_Atribute.add(obiect);
                                }
                            }
                        }
                        else
                        {
                                /// deja a fost creata o chestie la un moment dat in Noua_Instanta
                                /// trebuie sa clonam Noua_Instanta de-a binelea
                                Instanta Cea_Mai_Noua_Instanta = new Instanta();
                                for (Val_Atribut h : Noua_Instanta.Val_Atribute)
                                {
                                    Val_Atribut hClona = new Val_Atribut();
                                    hClona.type = h.type;
                                    hClona.x = h.x;
                                    hClona.y = h.y;
                                    hClona.z = h.z;

                                    Cea_Mai_Noua_Instanta.Val_Atribute.add(hClona);
                                }
                                Noua_Instanta = Cea_Mai_Noua_Instanta;
                        }

                            /// inainte sa o inserez caut Noua_Instanta sa vad de cate ori apare prin toata baza de date
                            int de_cate_ori_gasit = 0;  /// pe fiecare nod
                            for (Nod ii : DB.Noduri)
                            {
                                int gasit = 0;  /// pe nodul curent
                                for (Entitate jj : ii.Entitati)
                                {
                                    if (jj.nume.equals(j.nume))
                                    {
                                        for (Instanta kk : jj.Instante)
                                        {
                                            if (kk.Val_Atribute.get(0).type == Noua_Instanta.Val_Atribute.get(0).type)
                                            {
                                                if (kk.Val_Atribute.get(0).type == 0)
                                                    if (kk.Val_Atribute.get(0).x == Noua_Instanta.Val_Atribute.get(0).x)
                                                        gasit = 1;

                                                if (kk.Val_Atribute.get(0).type == 1)
                                                    if (kk.Val_Atribute.get(0).y == Noua_Instanta.Val_Atribute.get(0).y)
                                                        gasit = 1;

                                                if (kk.Val_Atribute.get(0).type == 2 )
                                                    if (kk.Val_Atribute.get(0).z == Noua_Instanta.Val_Atribute.get(0).z)
                                                        gasit = 1;
                                            }
                                        }
                                    }
                                }
                                if (gasit == 1)
                                {
                                    de_cate_ori_gasit++;
                                }
                            }

                            if (de_cate_ori_gasit < j.RF)
                            {
                                j.Instante.add(0, Noua_Instanta);
                                Instanta_si_Atribut_de_Printat Noua_Instanta_de_Printat = new Instanta_si_Atribut_de_Printat();
                                Noua_Instanta_de_Printat.nume = j.nume;
                                Noua_Instanta_de_Printat.instance = Noua_Instanta.Val_Atribute;
                                Noua_Instanta_de_Printat.Atribute = j.Atribute;

                                i.Instante_de_Printat.add(0, Noua_Instanta_de_Printat);
                            }
                    }
                }
            }
        }


        public void Sterge_Instanta(String line, DataBase DB, PrintWriter pw)
        {
            String[] parts = line.split(" ");
            String nume_ent = parts[0];
            String key = parts[1];

            int found = 0;
            for (Nod i : DB.Noduri)
            {
                for (Instanta_si_Atribut_de_Printat j : i.Instante_de_Printat)
                {
                    if (nume_ent.equals(j.nume))
                    {
                        int type = 2;
                        int cate_puncte = 0;
                        for (int iterator = 0; iterator < key.length() && type == 2; iterator++)
                        {
                            if (!((key.charAt(iterator) > '0' && key.charAt(iterator) < '9') || key.charAt(iterator) == '.'))
                                type = 1;

                            if (key.charAt(iterator) == '.')
                                cate_puncte++;
                        }

                        if (cate_puncte == 0 && type == 2)
                            type = 1;   /// numar int
                        else
                        if (cate_puncte == 1 && type == 2)
                            type = 2;   /// numar float
                        else
                            type = 0;   /// numar String

                        /// am o sansa sa gasesc instanta asta -> hai sa o caut aici
                        if (type == 1 && j.instance.get(0).x == Integer.parseInt(key))
                        {
                            found = 1;
                            for (Entitate k : i.Entitati)
                            {
                                if (k.nume.equals(j.nume))
                                {
                                    for (Instanta h : k.Instante)
                                    {
                                        if (j.instance.get(0).x == h.Val_Atribute.get(0).x)
                                        {
                                           k.Instante.remove(h);
                                           break;
                                        }
                                    }
                                }
                            }

                            i.Instante_de_Printat.remove(j);
                            break;
                        }
                        else
                        if (type == 0 && j.instance.get(0).y.equals(key))
                        {
                            found = 1;
                            for (Entitate k : i.Entitati)
                            {
                                if (k.nume.equals(j.nume))
                                {
                                    for (Instanta h : k.Instante)
                                    {
                                        if (j.instance.get(0).y == h.Val_Atribute.get(0).y)
                                        {
                                           k.Instante.remove(h);
                                           break;
                                        }
                                    }
                                }
                            }

                            i.Instante_de_Printat.remove(j);
                            break;
                        }
                        else
                        if (type == 2 && j.instance.get(0).z == Float.parseFloat(key))
                        {
                            found = 1;
                            for (Entitate k : i.Entitati)
                            {
                                if (k.nume.equals(j.nume))
                                {
                                    for (Instanta h : k.Instante)
                                    {
                                        if (j.instance.get(0).z == h.Val_Atribute.get(0).z)
                                        {
                                           k.Instante.remove(h);
                                           break;
                                        }
                                    }
                                }
                            }

                            i.Instante_de_Printat.remove(j);
                            break;
                        }
                    }
                }

            }
            if (found == 0)
                pw.print("NO INSTANCE TO DELETE");
        }




        public void Update_Instanta(String line, DataBase DB)
        {
            String[] parts = line.split(" ");
            int el_curent = 2;
            int found = 0;
            String nume_ent = parts[0];
            String key = parts[1];

            for (Nod i : DB.Noduri)
            {
                for (Instanta_si_Atribut_de_Printat j : i.Instante_de_Printat)
                {
                    if (nume_ent.equals(j.nume))
                    {
                        int type = 2; /// presupun ca e float -> verific daca contine . si numere
                        int cate_puncte = 0;
                        for (int iterator = 0; iterator < key.length() && type == 2; iterator++)
                        {
                            if (!((key.charAt(iterator) > '0' && key.charAt(iterator) < '9') || key.charAt(iterator) == '.'))
                                type = 1;

                            if (key.charAt(iterator) == '.')
                                cate_puncte++;
                        }

                        if (cate_puncte == 0 && type == 2)
                            type = 1;   /// numar int
                        else
                        if (cate_puncte == 1 && type == 2)
                            type = 2;   /// numar float
                        else
                            type = 0;   /// numar String

                        /// cand dau update modific si pozitia in vector
                        if (type == 1 && j.instance.get(0).x == Integer.parseInt(key))
                        {
                            Instanta Noua_Instanta = new Instanta();
                            Instanta_si_Atribut_de_Printat Noua_Instanta_de_Printat = new Instanta_si_Atribut_de_Printat();

                            for (int num = 0; num < j.Atribute.size(); num++)
                            {
                                 if (j.Atribute.get(num).type == 0)
                                {
                                    Val_Atribut obiect = new Val_Atribut();
                                    obiect.type = 0;
                                    obiect.x = Integer.parseInt(parts[el_curent++]);
                                    Noua_Instanta.Val_Atribute.add(obiect);
                                }
                            }

                            Noua_Instanta_de_Printat.nume = j.nume;
                            Noua_Instanta_de_Printat.instance = Noua_Instanta.Val_Atribute;
                            Noua_Instanta_de_Printat.Atribute = j.Atribute;

                            i.Instante_de_Printat.add(0, Noua_Instanta_de_Printat);
                            i.Instante_de_Printat.remove(j);
                            break;
                        }
                        else
                        if (type == 0 && j.instance.get(0).y == key)
                        {
                            Instanta Noua_Instanta = new Instanta();
                            Instanta_si_Atribut_de_Printat Noua_Instanta_de_Printat = new Instanta_si_Atribut_de_Printat();

                            for (int num = 0; num < j.Atribute.size(); num++)
                            {
                                 if (j.Atribute.get(num).type == 0)
                                {
                                    Val_Atribut obiect = new Val_Atribut();
                                    obiect.type = 0;
                                    obiect.y = parts[el_curent++];
                                    Noua_Instanta.Val_Atribute.add(obiect);
                                }
                            }

                            Noua_Instanta_de_Printat.nume = j.nume;
                            Noua_Instanta_de_Printat.instance = Noua_Instanta.Val_Atribute;
                            Noua_Instanta_de_Printat.Atribute = j.Atribute;

                            i.Instante_de_Printat.add(0, Noua_Instanta_de_Printat);
                            i.Instante_de_Printat.remove(j);
                            break;
                        }
                        else
                        if (type == 2 && j.instance.get(0).z == Float.parseFloat(key))
                        {
                            Instanta Noua_Instanta = new Instanta();
                            Instanta_si_Atribut_de_Printat Noua_Instanta_de_Printat = new Instanta_si_Atribut_de_Printat();

                            for (int num = 0; num < j.Atribute.size(); num++)
                            {
                                 if (j.Atribute.get(num).type == 0)
                                {
                                    Val_Atribut obiect = new Val_Atribut();
                                    obiect.type = 0;
                                    obiect.z = Float.parseFloat(parts[el_curent++]);
                                    Noua_Instanta.Val_Atribute.add(obiect);
                                }
                            }

                            Noua_Instanta_de_Printat.nume = j.nume;
                            Noua_Instanta_de_Printat.instance = Noua_Instanta.Val_Atribute;
                            Noua_Instanta_de_Printat.Atribute = j.Atribute;

                            i.Instante_de_Printat.add(0, Noua_Instanta_de_Printat);
                            i.Instante_de_Printat.remove(j);
                            break;
                        }
                   }
                }
            }
        }
}
