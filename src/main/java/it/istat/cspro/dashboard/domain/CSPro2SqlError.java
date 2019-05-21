package it.istat.cspro.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Istat Cooperation Unit
 */
@Entity
@Table(name = "CSPRO2SQL_ERROR")
public class CSPro2SqlError implements Serializable {

    private static final long serialVersionUID = 6548347058368655334L;

    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "DICTIONARY")
    private int dictionary;
    @Column(name = "ERROR")
    private String error;
    @Column(name = "DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date date;
    @Column(name = "QUESTIONNAIRE")
    private String questionnaire;
    @Column(name = "SQL_SCRIPT")
    private String sqlScript;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDictionary() {
        return dictionary;
    }

    public void setDictionary(int dictionary) {
        this.dictionary = dictionary;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getSqlScript() {
        return sqlScript;
    }

    public void setSqlScript(String sqlScript) {
        this.sqlScript = sqlScript;
    }

}
