/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Models.LoaiMon;
import Ulties.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fuoc
 */
public class LoaiMonRepository {

    public void insert(LoaiMon model) {
        String sql = "INSERT INTO LOAIMON (MaLoai, TenLoai) VALUES (?, ?)";
        DBContext.executeUpdate(sql, model.getMaLoaiMon(), model.getTenLoaiMon());
    }

    public void update(LoaiMon model) {
        String sql = "UPDATE LOAIMON SET TenLoai=? WHERE MaLoai=?";
        DBContext.executeUpdate(sql, model.getTenLoaiMon(), model.getMaLoaiMon());
    }

    public void delete(String MaloaiMon) {
        String sql = "DELETE FROM LOAIMON WHERE MaLoai=?";
        DBContext.executeUpdate(sql, MaloaiMon);
    }

    public LoaiMon findByten(String manv) {
        String sql = "SELECT * FROM LOAIMON WHERE TenLoai=?";
        List<LoaiMon> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<LoaiMon> select() {
        String sql = "SELECT * FROM LOAIMON";
        return select(sql);
    }

    public LoaiMon findById(String manv) {
        String sql = "SELECT * FROM LOAIMON WHERE MaLoai=?";
        List<LoaiMon> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<LoaiMon> select(String sql, Object... args) {
        List<LoaiMon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = DBContext.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiMon model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private LoaiMon readFromResultSet(ResultSet rs) throws SQLException {
        LoaiMon model = new LoaiMon();
        model.setMaLoaiMon(rs.getString("MaLoai"));
        model.setTenLoaiMon(rs.getString("TenLoai"));
        return model;
    }
}
