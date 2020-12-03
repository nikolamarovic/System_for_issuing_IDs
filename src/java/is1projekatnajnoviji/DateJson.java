/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1projekatnajnoviji;

/**
 *
 * @author Korisnik
 */
public class DateJson {
    private String poruka;
    private boolean dostupnost;
    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }

    public String getPoruka() {
        return poruka;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }

}
