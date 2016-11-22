/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Gino
 */
public class pruebaMecenas {
    public static void main(String[] args) throws Exception{
        LocalDate unaFecha = LocalDate.of(1990, 9, 8);
        Mecena miMecena = new Mecena("Adriana", "Japon", "Tokyo", unaFecha);
        MultiMecenas cuack = new MultiMecenas();
        cuack.crear("Diana", "Noruega", "Desamparados", LocalDate.of(1991, 7, 12));
    }
}
