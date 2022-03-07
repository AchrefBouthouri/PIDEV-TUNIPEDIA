package Entities;
import Entities.Event;
import Entities.Payement;
import Entities.Person;
import Services.EventService;
import Services.PayementService;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
//import tn.esprit.services.UserService;

/**
 *
 * @author Houssem
 */
public class Pdf {
    
    
    public void GeneratePdf(String filename,Payement P,Person p,int idevent) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        PayementService us=new PayementService();
            
        document.add(new Paragraph("            Facture :"));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));
       EventService Es = new EventService();
             Event e1 = Es.Selectevent(idevent);
       
        document.add(new Paragraph("Date :"+P.getDate()));
         document.add(new Paragraph("                      "));
        document.add(new Paragraph("Montant :"+P.getMontant()));
         document.add(new Paragraph("                      "));
        document.add(new Paragraph("La part de:"+p.getFullName()));
         document.add(new Paragraph("                      "));
        document.add(new Paragraph("Evenement :"+e1.getNom()));
  

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
         document.add(new Paragraph("                              Tunipedia2022                     "));
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}