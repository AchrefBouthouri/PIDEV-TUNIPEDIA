/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author user
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entities.Evaluation;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author user
 */
public class EvaluationService {

    Connection mc;
    PreparedStatement ste;

    public EvaluationService() {
        mc = ConnexionDB.getInstance().getCnx();

    }

    ;
  public void AjouterEvaluation(Evaluation E) {

        String sql = "insert into evaluation(CreatedAt,Notice,Comment,CreatedBy,Place_id) values(now(),?,?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, E.getNotice());
            ste.setString(2, E.getComment());
            ste.setInt(3, E.getCreatedBy());
            ste.setInt(4, E.getPlace_id());
            ste.executeUpdate();
            System.out.println("Evalution ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    ;
  public List<Evaluation> afficherEvaluation() {
        List<Evaluation> Evaluations = new ArrayList<>();
        String sql = "SELECT E.notice,E.CreatedAt, E.comment,E.Createdby,p.Name from Evaluation AS E INNER JOIN Place AS p ON E.Place_id=p.id";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Evaluation E = new Evaluation();
                E.setNotice(rs.getInt(1));
                E.setCreatedAt(rs.getDate(2));
                E.setComment(rs.getString(3));
                E.setCreatedBy(rs.getInt(4));

                E.setLocation(rs.getString(5));
                Evaluations.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return Evaluations;
    }

    public void UpdateDte(Date CreatedAt, Evaluation E) {
        String sql = "UPDATE  Evaluation set CreatedAt=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setDate(1, CreatedAt);
            ste.setInt(2, E.getId());
            ste.executeUpdate();
            System.out.println("La date de creation  mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ;
        
   public void UpdateNotice(int Notice, Evaluation E) {
        String sql = "UPDATE  Evaluation set Notice=? where Id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, Notice);
            ste.setInt(2, E.getId());
            ste.executeUpdate();
            System.out.println("Notice est mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ;
   
   
   
    public void UpdateComment(String Comment, Evaluation E) {
        String sql = "UPDATE  Evaluation set Comment=? where Id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, Comment);
            ste.setInt(2, E.getId());
            ste.executeUpdate();
            System.out.println("Comment est mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerEvaluation(Evaluation E) {
        String sql = "delete from Evaluation where ID=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, E.getId());
            ste.executeUpdate();
            System.out.println("Evalution suprimmer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

};
