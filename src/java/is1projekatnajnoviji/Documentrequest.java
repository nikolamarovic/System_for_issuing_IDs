/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1projekatnajnoviji;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "documentrequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentrequest.findAll", query = "SELECT d FROM Documentrequest d"),
    @NamedQuery(name = "Documentrequest.findByInternalId", query = "SELECT d FROM Documentrequest d WHERE d.internalId = :internalId"),
    @NamedQuery(name = "Documentrequest.findById", query = "SELECT d FROM Documentrequest d WHERE d.id = :id"),
    @NamedQuery(name = "Documentrequest.findByIme", query = "SELECT d FROM Documentrequest d WHERE d.ime = :ime"),
    @NamedQuery(name = "Documentrequest.findByPrezime", query = "SELECT d FROM Documentrequest d WHERE d.prezime = :prezime"),
    @NamedQuery(name = "Documentrequest.findByJmbg", query = "SELECT d FROM Documentrequest d WHERE d.jmbg = :jmbg"),
    @NamedQuery(name = "Documentrequest.findByImeOca", query = "SELECT d FROM Documentrequest d WHERE d.imeOca = :imeOca"),
    @NamedQuery(name = "Documentrequest.findByPrezimeOca", query = "SELECT d FROM Documentrequest d WHERE d.prezimeOca = :prezimeOca"),
    @NamedQuery(name = "Documentrequest.findByImeMajke", query = "SELECT d FROM Documentrequest d WHERE d.imeMajke = :imeMajke"),
    @NamedQuery(name = "Documentrequest.findByPrezimeMajke", query = "SELECT d FROM Documentrequest d WHERE d.prezimeMajke = :prezimeMajke"),
    @NamedQuery(name = "Documentrequest.findByDatum", query = "SELECT d FROM Documentrequest d WHERE d.datum = :datum"),
    @NamedQuery(name = "Documentrequest.findByPol", query = "SELECT d FROM Documentrequest d WHERE d.pol = :pol"),
    @NamedQuery(name = "Documentrequest.findByBracnoStanje", query = "SELECT d FROM Documentrequest d WHERE d.bracnoStanje = :bracnoStanje"),
    @NamedQuery(name = "Documentrequest.findByNacionalnost", query = "SELECT d FROM Documentrequest d WHERE d.nacionalnost = :nacionalnost"),
    @NamedQuery(name = "Documentrequest.findByProfesija", query = "SELECT d FROM Documentrequest d WHERE d.profesija = :profesija"),
    @NamedQuery(name = "Documentrequest.findByOpstina", query = "SELECT d FROM Documentrequest d WHERE d.opstina = :opstina"),
    @NamedQuery(name = "Documentrequest.findByUlica", query = "SELECT d FROM Documentrequest d WHERE d.ulica = :ulica"),
    @NamedQuery(name = "Documentrequest.findByBroj", query = "SELECT d FROM Documentrequest d WHERE d.broj = :broj"),
    @NamedQuery(name = "Documentrequest.findByStatus", query = "SELECT d FROM Documentrequest d WHERE d.status = :status")})
public class Documentrequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InternalId")
    private Integer internalId;
    @Size(max = 50)
    @Column(name = "Id")
    private String id;
    @Size(max = 50)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 50)
    @Column(name = "Prezime")
    private String prezime;
    @Size(max = 50)
    @Column(name = "JMBG")
    private String jmbg;
    @Size(max = 50)
    @Column(name = "ImeOca")
    private String imeOca;
    @Size(max = 50)
    @Column(name = "PrezimeOca")
    private String prezimeOca;
    @Size(max = 50)
    @Column(name = "ImeMajke")
    private String imeMajke;
    @Size(max = 50)
    @Column(name = "PrezimeMajke")
    private String prezimeMajke;
    @Size(max = 50)
    @Column(name = "Datum")
    private String datum;
    @Size(max = 50)
    @Column(name = "Pol")
    private String pol;
    @Size(max = 50)
    @Column(name = "BracnoStanje")
    private String bracnoStanje;
    @Size(max = 50)
    @Column(name = "Nacionalnost")
    private String nacionalnost;
    @Size(max = 50)
    @Column(name = "Profesija")
    private String profesija;
    @Size(max = 50)
    @Column(name = "Opstina")
    private String opstina;
    @Size(max = 50)
    @Column(name = "Ulica")
    private String ulica;
    @Size(max = 50)
    @Column(name = "Broj")
    private String broj;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;

    public Documentrequest() {
    }

    public Documentrequest(Integer internalId) {
        this.internalId = internalId;
    }

    public Integer getInternalId() {
        return internalId;
    }

    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImeOca() {
        return imeOca;
    }

    public void setImeOca(String imeOca) {
        this.imeOca = imeOca;
    }

    public String getPrezimeOca() {
        return prezimeOca;
    }

    public void setPrezimeOca(String prezimeOca) {
        this.prezimeOca = prezimeOca;
    }

    public String getImeMajke() {
        return imeMajke;
    }

    public void setImeMajke(String imeMajke) {
        this.imeMajke = imeMajke;
    }

    public String getPrezimeMajke() {
        return prezimeMajke;
    }

    public void setPrezimeMajke(String prezimeMajke) {
        this.prezimeMajke = prezimeMajke;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getBracnoStanje() {
        return bracnoStanje;
    }

    public void setBracnoStanje(String bracnoStanje) {
        this.bracnoStanje = bracnoStanje;
    }

    public String getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public String getProfesija() {
        return profesija;
    }

    public void setProfesija(String profesija) {
        this.profesija = profesija;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (internalId != null ? internalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentrequest)) {
            return false;
        }
        Documentrequest other = (Documentrequest) object;
        if ((this.internalId == null && other.internalId != null) || (this.internalId != null && !this.internalId.equals(other.internalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is1projekatnajnoviji.Documentrequest[ internalId=" + internalId + " ]";
    }
    
}
