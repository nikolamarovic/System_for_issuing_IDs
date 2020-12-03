package is1projekatnajnoviji;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Korisnik extends JFrame {
	private static final long serialVersionUID = 1L;
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
        private String regCentarId;
        private String idDokument;
        private JTextField txtDokument = new JTextField();
        private Documentrequest data = new Documentrequest();
        private EntityManager em;
	public Korisnik() {
		super("Regionalni centar");
		setResizable(false);
		setBounds(100,100,900,850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
                
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("IS1PROJEKATNAJNOVIJIPU");
                        
                em = emf.createEntityManager();
		
                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBounds(0, 0, 900, 850);
                JComponent tab1 = addRegionalniCentar();
                tabbedPane.add("Regionalni centar", tab1);
                JComponent tab2 = addProvera();
                tabbedPane.add("Provera",tab2);
                
                add(tabbedPane);
		setVisible(true);
	}
	
        public JPanel addProvera(){
            JPanel panel = new JPanel();
            panel.setBounds(0,0,901,850);
            panel.setLayout(null);
            
            JLabel labelStatus = new JLabel();
            labelStatus.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
            labelStatus.setBounds(300, 250, 400, 50);
            labelStatus.setVisible(false);
            panel.add(labelStatus);
            
            JButton buttonProveriStatus = new JButton("Proveri status");
            buttonProveriStatus.setBounds(300,200,300,50);
            buttonProveriStatus.setFont(new Font("Helvetica Neue", Font.PLAIN,25));
            
            JButton buttonUruciZahtev = new JButton("Uruci zahtev");
            buttonUruciZahtev.setBounds(300,400,300,50);
            buttonUruciZahtev.setFont(new Font("Helvetica Neue", Font.PLAIN,25));
            buttonUruciZahtev.setVisible(false);
            
            buttonProveriStatus.addActionListener((ActionEvent e) -> {
                data.setStatus("cekaNaUrucenje");
                labelStatus.setText("Status: " + data.getStatus());
                labelStatus.setForeground(Color.DARK_GRAY);
                labelStatus.setVisible(true);      
                
                try {
                    DocumentResponse status = checkStatus();
                    System.out.println(status.getStatus());
                    if(status.getStatus().equals("proizveden")) buttonUruciZahtev.setVisible(true);
                } catch (IOException ex) { System.out.println("GRESKA NULL");}
            });
            
            JLabel labelUruci = new JLabel();
            labelUruci.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
            labelUruci.setBounds(300, 450, 400, 50);
            labelUruci.setVisible(false);
            panel.add(labelUruci);
            
            buttonUruciZahtev.addActionListener((ActionEvent e) -> {
                
                data.setStatus("urucen");
                labelUruci.setText("Zahtev je uspesno urucen !");
                labelUruci.setForeground(Color.GREEN);
                labelUruci.setVisible(true);      
            });
       
            panel.add(buttonProveriStatus);
            panel.add(buttonUruciZahtev);
                 
            return panel;
        }
        
	public JPanel addRegionalniCentar() {
            
                JPanel panel = new JPanel();
		panel.setBounds(0,0,901,750);
		panel.setLayout(null);
		
		JLabel podaci = new JLabel("    Unesite podatke:");
		podaci.setForeground(Color.BLACK);
		podaci.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		podaci.setBounds(300,0,300,50);

		JLabel labelIme = new JLabel("Ime:");
		labelIme.setForeground(Color.BLACK);
		labelIme.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelIme.setBounds(25,70,200,30);
	
		JTextField txtIme = new JTextField();
		txtIme.setBounds(225,70,200,30);
		
		JLabel labelPrezime = new JLabel("Prezime:");
		labelPrezime.setForeground(Color.BLACK);
		labelPrezime.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelPrezime.setBounds(475,70,200,30);
		
		JTextField txtPrezime = new JTextField();
		txtPrezime.setBounds(675,70,200,30);
		
		JLabel labelJmbg = new JLabel("JMBG:");
		labelJmbg.setForeground(Color.BLACK);
		labelJmbg.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelJmbg.setBounds(25,120,200,30);
	
		JTextField txtJmbg = new JTextField();
		txtJmbg.setBounds(225,120,200,30);
		
		JLabel labelDatum= new JLabel("Datum rodjenja:");
		labelDatum.setForeground(Color.BLACK);
		labelDatum.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelDatum.setBounds(475,120,200,30);
	 
		JLabel labelDan= new JLabel("Dan:");
		labelDan.setForeground(Color.BLACK);
		labelDan.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		labelDan.setBounds(675,98,60,30);

		JTextField txtDan = new JTextField();
		txtDan.setBounds(675,120,60,30);
		
		JLabel labelMesec= new JLabel("Mesec:");
		labelMesec.setForeground(Color.BLACK);
		labelMesec.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		labelMesec.setBounds(740,98,60,30);

		JTextField txtMesec = new JTextField();
		txtMesec.setBounds(740,120,60,30);
		
		JLabel labelGodina= new JLabel("Godina:");
		labelGodina.setForeground(Color.BLACK);
		labelGodina.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		labelGodina.setBounds(805,98,60,30);
		
		JTextField txtGodina = new JTextField();
		txtGodina.setBounds(805,120,70,30);
		
		JLabel labelPol = new JLabel("Pol:");
		labelPol.setForeground(Color.BLACK);
		labelPol.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelPol.setBounds(25,170,200,30);
		
		JRadioButton buttonMusko = new JRadioButton("Muško");
		buttonMusko.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		buttonMusko.setBounds(225, 170, 100, 30);
		buttonMusko.addActionListener((ActionEvent e) -> {
                    data.setPol("musko");
                });
		
		JRadioButton buttonZensko = new JRadioButton("Žensko");
		buttonZensko.setBounds(325, 170, 100, 30);
		buttonZensko.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		buttonZensko.addActionListener((ActionEvent e) -> {
                    data.setPol("zensko");
                });
		ButtonGroup groupPol = new ButtonGroup();
		groupPol.add(buttonMusko);
		groupPol.add(buttonZensko);
		
		JLabel idDokumentLabel = new JLabel("ID regionalnog centra:");
                idDokumentLabel.setForeground(Color.BLACK);
                idDokumentLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
                idDokumentLabel.setBounds(25,220,200,30);

                txtDokument.setBounds(225,220,200,30);

		JLabel labelBracnoStanje = new JLabel("Bračno stanje:");
		labelBracnoStanje.setForeground(Color.BLACK);
		labelBracnoStanje.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelBracnoStanje.setBounds(475,170,200,30);
		
		JRadioButton buttonNeozenjen = new JRadioButton("Neoženjen/Neudata");
		buttonNeozenjen.setFont(new Font("Helvetica Neue", Font.BOLD, 12 ));
		buttonNeozenjen.setBounds(675, 170, 100, 30);
		buttonNeozenjen.addActionListener((ActionEvent e) -> {
                    data.setBracnoStanje("neozenjen/a");
                });
		JRadioButton buttonOzenjen = new JRadioButton("Oženjen/Udata");
		buttonOzenjen.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
		buttonOzenjen.setBounds(775, 170, 100, 30);
		buttonOzenjen.addActionListener((ActionEvent e) -> {
                    data.setBracnoStanje("ozenjen/udata");
                });
		
		JRadioButton buttonRazveden = new JRadioButton("Razveden/a");
		buttonRazveden.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
		buttonRazveden.setBounds(675, 220, 100, 30);
		buttonRazveden.addActionListener((ActionEvent e) -> {
                    data.setBracnoStanje("rezveden/a");
                });
		JRadioButton buttonUdovac = new JRadioButton("Udovac/ica");		
		buttonUdovac.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
		buttonUdovac.setBounds(775, 220, 100, 30);
		buttonUdovac.addActionListener((ActionEvent e) -> {
                    data.setBracnoStanje("udovac/udovica");
                });
		
		ButtonGroup groupBracnoStanje = new ButtonGroup();
		groupBracnoStanje.add(buttonNeozenjen);
		groupBracnoStanje.add(buttonUdovac);
		groupBracnoStanje.add(buttonRazveden);
		groupBracnoStanje.add(buttonNeozenjen);
		
		
		JLabel labelNacionalnost= new JLabel("Nacionalnost:");
		labelNacionalnost.setForeground(Color.DARK_GRAY);
		labelNacionalnost.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelNacionalnost.setBounds(475,320,200,30);
		
		JTextField txtNacionalnost = new JTextField();
		txtNacionalnost.setBounds(675,320,200,30);
		
		JLabel labelProfesija = new JLabel("Profesija:");
		labelProfesija.setForeground(Color.DARK_GRAY);
		labelProfesija.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelProfesija.setBounds(475,370,200,30);
		
		JTextField txtProfesija = new JTextField();
		txtProfesija.setBounds(675,370,200,30);
		
		JLabel labelPrebivaliste = new JLabel("Dodatni podaci:");
		labelPrebivaliste.setForeground(Color.BLACK);
		labelPrebivaliste.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
		labelPrebivaliste.setBounds(25, 270, 200, 30);
		
		JLabel labelOpstina = new JLabel("Opština:");
		labelOpstina.setForeground(Color.DARK_GRAY);
		labelOpstina.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelOpstina.setBounds(25, 320, 200, 30);
		
		JTextField txtOpstina= new JTextField();
		txtOpstina.setBounds(225,320,200,30);
		
		JLabel labelUlica = new JLabel("Ulica:");
		labelUlica.setForeground(Color.DARK_GRAY);
		labelUlica.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelUlica.setBounds(25, 370, 200, 30);
		
		JTextField txtUlica = new JTextField();
		txtUlica.setBounds(225,370,200,30);
		
		JLabel labelBroj = new JLabel("Broj:");
		labelBroj.setForeground(Color.DARK_GRAY);
		labelBroj.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelBroj.setBounds(25, 420, 200, 30);
		
		JTextField txtBroj = new JTextField();
		txtBroj.setBounds(225,420,200,30);
		
		JLabel labelDodatniPodaci = new JLabel("Dodatni podaci:");
		labelDodatniPodaci.setForeground(Color.BLACK);
		labelDodatniPodaci.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
		labelDodatniPodaci.setBounds(25, 420, 200, 30);
		
		JLabel labelImeOca = new JLabel("Ime oca:");
		labelImeOca.setForeground(Color.DARK_GRAY);
		labelImeOca.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelImeOca.setBounds(25, 520, 200, 30);
		
		JTextField txtImeOca= new JTextField();
		txtImeOca.setBounds(225,520,200,30);
		
		JLabel labelPrezimeOca = new JLabel("Prezime oca:");
		labelPrezimeOca.setForeground(Color.DARK_GRAY);
		labelPrezimeOca.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelPrezimeOca.setBounds(475,520,200,30);
		
		JTextField txtPrezimeOca = new JTextField();
		txtPrezimeOca.setBounds(675,520,200,30);
		
		JLabel labelImeMajke = new JLabel("Ime majke:");
		labelImeMajke.setForeground(Color.DARK_GRAY);
		labelImeMajke.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelImeMajke.setBounds(25, 570, 200, 30);
		
		JTextField txtImeMajke= new JTextField();
		txtImeMajke.setBounds(225,570,200,30);
		
		JLabel labelPrezimeMajke = new JLabel("Prezime majke:");
		labelPrezimeMajke.setForeground(Color.DARK_GRAY);
		labelPrezimeMajke.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		labelPrezimeMajke.setBounds(475,570,200,30);
		
		JTextField txtPrezimeMajke = new JTextField();
		txtPrezimeMajke.setBounds(675,570,200,30);
                
                
                JLabel labelZahtev = new JLabel();
                labelZahtev.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                labelZahtev.setBounds(300, 670, 400, 50);
                labelZahtev.setVisible(false);
                panel.add(labelZahtev);
                        
                JButton buttonPosaljiZahtev = new JButton("Posalji zahtev");
                buttonPosaljiZahtev.setBounds(300,620,300,50);
                buttonPosaljiZahtev.setFont(new Font("Helvetica Neue", Font.PLAIN,25));
                buttonPosaljiZahtev.addActionListener((ActionEvent e) -> {
                    
                    data.setIme(txtIme.getText());
                    data.setPrezime(txtPrezime.getText());
                    data.setJmbg(txtJmbg.getText());
                    String datum = txtDan.getText()+"-"+txtMesec.getText()+"-"+txtGodina.getText();
                    data.setDatum(datum);
                    data.setOpstina(txtOpstina.getText());
                    data.setUlica(txtUlica.getText());
                    data.setBroj(txtBroj.getText());
                    data.setNacionalnost(txtNacionalnost.getText());
                    data.setProfesija(txtProfesija.getText());
                    data.setImeOca(txtImeOca.getText());
                    data.setPrezimeOca(txtPrezimeOca.getText());
                    data.setImeMajke(txtImeMajke.getText());
                    data.setPrezimeMajke(txtPrezimeMajke.getText());                                     
                    data.setId(idDokument);
                    data.setStatus("kreiran");
                    
                    labelZahtev.setVisible(false);
                    try {
                        regCentarId = txtDokument.getText();
                        if(checkPeriod()){  
                            em.getTransaction().begin();
                            em.persist(data);
                            em.flush();
                            em.getTransaction().commit();
                            
                            if(data.getInternalId()>9) idDokument = regCentarId + "00000" + data.getInternalId();
                            else idDokument = regCentarId + "000000" + data.getInternalId();
                            
                            if(sendPostRequest()){
                            
                                data.setStatus("uProdukciji");

                                labelZahtev.setText("Zahtev poslat na obradu.");
                                labelZahtev.setForeground(Color.GREEN);
                                labelZahtev.setVisible(true);
                            }else{
                                labelZahtev.setText("Los zahtev!");
                                labelZahtev.setForeground(Color.RED);
                                labelZahtev.setVisible(true);
                            }
                        }else{
                            labelZahtev.setText("Nedostupan termin!");
                            labelZahtev.setForeground(Color.RED);
                            labelZahtev.setVisible(true);
                        }  
                    } catch (IOException ex) { }
            
                });
                
		panel.add(podaci);
		panel.add(labelIme); panel.add(txtIme);
		panel.add(labelPrezime); panel.add(txtPrezime);
		panel.add(labelJmbg); panel.add(txtJmbg);
		panel.add(labelDatum);
		panel.add(labelDan);
		panel.add(txtDan); 
		panel.add(labelMesec);
		panel.add(txtMesec); 
		panel.add(labelGodina);
		panel.add(txtGodina);
		panel.add(labelPol);
		panel.add(buttonMusko);
		panel.add(buttonZensko);
                panel.add(idDokumentLabel);
                panel.add(txtDokument);
		panel.add(labelBracnoStanje);
		panel.add(buttonUdovac);
		panel.add(buttonRazveden);
		panel.add(buttonOzenjen);
		panel.add(buttonNeozenjen);
		panel.add(labelNacionalnost);
		panel.add(txtNacionalnost);
		panel.add(labelProfesija);
		panel.add(txtProfesija);
		panel.add(labelPrebivaliste);
		panel.add(labelOpstina);
		panel.add(labelBroj);
		panel.add(labelUlica);
		panel.add(txtOpstina);
		panel.add(txtBroj);
		panel.add(txtUlica);
		panel.add(txtImeOca);
		panel.add(labelImeOca);
		panel.add(txtPrezimeOca);
		panel.add(labelPrezimeOca);
		panel.add(txtImeMajke);
		panel.add(labelImeMajke);
		panel.add(txtPrezimeMajke);
		panel.add(labelPrezimeMajke);
		panel.add(buttonPosaljiZahtev);
                
		return panel;
	}
        
        public DocumentResponse checkStatus() throws IOException{
            HttpGet request = new HttpGet("http://collabnet.netset.rs:8081/is/persoCentar/"+idDokument);
            request.setHeader("Content-type","application/json");
            
            try(CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                String finalResult = result.replaceAll("JMBG", "jmbg");
                ObjectMapper objectMapper = new ObjectMapper();
                
                DocumentResponse status = objectMapper.readValue(finalResult, DocumentResponse.class);
                
                return status;
            }
        }
        public boolean sendPostRequest() throws IOException{
            StringBuilder sb = new StringBuilder();
            sb.append("http://collabnet.netset.rs:8081/is/persoCentar/submit");
            
            HttpPost request = new HttpPost(sb.toString());
            
            ObjectMapper objectMapper = new ObjectMapper();
            DataJson dataJson = new DataJson();
            dataJson.setBracnoStanje(data.getBracnoStanje());
            dataJson.setIme(data.getIme());
            dataJson.setPrezime(data.getPrezime());
            dataJson.setImeMajke(data.getImeMajke());
            dataJson.setPrezimeMajke(data.getPrezimeMajke());
            dataJson.setImeOca(data.getImeOca());
            dataJson.setPrezimeOca(data.getPrezimeOca());
            dataJson.setProfesija(data.getProfesija());
            dataJson.setNacionalnost(data.getNacionalnost());
            dataJson.setJMBG(data.getJmbg());
            dataJson.setDatumRodjenja(data.getDatum());
            dataJson.setBrojPrebivalista(data.getBroj());
            dataJson.setOpstinaPrebivalista(data.getOpstina());
            dataJson.setUlicaPrebivalista(data.getUlica());
            dataJson.setPol(data.getPol());
            dataJson.setId(idDokument);
            String stringData = objectMapper.writeValueAsString(dataJson);
            String replace = stringData.replaceAll("jmbg", "JMBG");
            StringEntity stringEntity = new StringEntity(replace);
            
            System.out.println(replace);
            request.setHeader("Content-type","application/json");        
            request.setEntity(stringEntity);
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                int status = response.getStatusLine().getStatusCode();
                String result = EntityUtils.toString(entity); 
                System.out.println(status + " " + result);
                if(status == 200) return true;
                else return false;   
            }           
        }       
        public boolean checkPeriod() throws IOException{     
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            StringBuilder sb = new StringBuilder();
            sb.append("http://collabnet.netset.rs:8081/is/terminCentar/checkTimeslotAvailability?regionalniCentarId=");
            sb.append(regCentarId).append("&termin=")/*.append(now.toString())*/.append("2020-02-24T16:45:00");
            System.out.println(sb.toString()); 
                    
            HttpGet request = new HttpGet(sb.toString());
            request.setHeader("Content-type","application/json");
            try(CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                
                String result = EntityUtils.toString(entity);
                System.out.println(result);

                ObjectMapper objectMapper = new ObjectMapper();

                DateJson dateJson = objectMapper.readValue(result,DateJson.class);
                System.out.println(dateJson.getPoruka() + (dateJson.isDostupnost()?"DOSTUPAN":"NEDOSTUPAN"));
                return dateJson.isDostupnost();
            }   
        }
        
	public static void main(String[] args) {
		Korisnik korisnik = new Korisnik();
	}

}
