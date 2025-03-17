package bitcamp.myapp.dao;

import bitcamp.myapp.vo.AttachedFile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLBoardFileDao implements BoardFileDao {

    private Connection con;

    public MySQLBoardFileDao(Connection con) {
        this.con = con;
    }

    @Override
    public int insert(AttachedFile attachedFile) {
        String sql = "insert into ed_attach_file(board_id, filename, origin_filename) values(" +
                attachedFile.getBoardNo() + ",'" + attachedFile.getFilename() + "','" + attachedFile.getOriginFilename() + "')";

        try (Statement stmt = con.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public AttachedFile findByNo(int fileNo) throws DaoException {
        String sql = "select af_id, board_id, filename, origin_filename from ed_attach_file where af_id = " + fileNo;

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return null;
            }
            AttachedFile attachedFile = new AttachedFile();
            attachedFile.setBoardNo(rs.getInt("board_id"));
            attachedFile.setFilename(rs.getString("filename"));
            attachedFile.setOriginFilename(rs.getString("origin_filename"));
            return attachedFile;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(int fileNo) {
        String sql = "delete from ed_attach_file where af_id = " + fileNo;

        try (Statement stmt = con.createStatement()) {
            int i = stmt.executeUpdate(sql);
            return i;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
