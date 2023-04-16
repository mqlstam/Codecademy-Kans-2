package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codecademy.database.DbConnection;

/**
 * The WebcastDAOImpl class implements the WebcastDAO interface and provides
 * methods for accessing and manipulating
 * webcast data stored in a database. This class uses a DbConnection object to
 * establish a connection to the database.
 */
public class WebcastDAOImpl implements WebcastDAO {

    private DbConnection dbConnection;

    /**
     * Constructs a new WebcastDAOImpl object with the given DbConnection.
     *
     * @param dbConnection the DbConnection object used to establish a connection to
     *                     the database
     */
    public WebcastDAOImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Retrieves a list of the top 3 most viewed webcasts, sorted by number of views
     * in descending order.
     *
     * @return a list of the top 3 most viewed webcasts, formatted as "webcastTitle
     *         (views)"
     */
    @Override
    public List<String> getTop3ViewedWebcasts() {
        List<String> top3Webcasts = new ArrayList<>();

        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement(
                    "SELECT TOP 3 w.WebcastTitle, COUNT(*) as Views " +
                            "FROM Student_Content sc " +
                            "JOIN Webcast w ON sc.ContentID = w.ContentID " +
                            "GROUP BY w.WebcastTitle " +
                            "ORDER BY Views DESC");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String webcastTitle = rs.getString("WebcastTitle");
                int views = rs.getInt("Views");
                String result = webcastTitle + " (" + views + " views)";
                top3Webcasts.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return top3Webcasts;
    }

}
