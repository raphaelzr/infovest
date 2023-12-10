package br.com.senac.quarkus.projetofullstack.repository;

import br.com.senac.quarkus.projetofullstack.entity.PrecoDiarioAtivo;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PrecoDiarioAtivoRepository {

    @ConfigProperty(name = "quarkus.datasource.url")
    String url;
    @ConfigProperty(name = "quarkus.datasource.username")
    String username;
    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    public List<PrecoDiarioAtivo> getAllPrecosDiariosAtivos() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<PrecoDiarioAtivo> precosDiariosAtivos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_PRECO, DT_ATIVO, DEC_VALOR, ID_ATIVO FROM TB_PRECO_DIARIO_ATIVO");
            rs = ps.executeQuery();
            while (rs.next()) {
                PrecoDiarioAtivo precoDiarioAtivo = new PrecoDiarioAtivo();

                precoDiarioAtivo.setIdPreco(rs.getInt(1));
                precoDiarioAtivo.setData(rs.getDate(2).toLocalDate());
                precoDiarioAtivo.setValor(rs.getBigDecimal(3));
                precoDiarioAtivo.setIdAtivo(rs.getInt(4));

                precosDiariosAtivos.add(precoDiarioAtivo);
            }
            rs.close();
            ps.close();
            conn.close();
            return precosDiariosAtivos;
        } catch (SQLException e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return precosDiariosAtivos;
    }

    public PrecoDiarioAtivo getPrecoDiarioAtivoById(Integer id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PrecoDiarioAtivo precoDiarioAtivo = new PrecoDiarioAtivo();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_PRECO, DT_ATIVO, DEC_VALOR, ID_ATIVO FROM TB_PRECO_DIARIO_ATIVO WHERE ID_PRECO = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                precoDiarioAtivo.setIdPreco(rs.getInt(1));
                precoDiarioAtivo.setData(rs.getDate(2).toLocalDate());
                precoDiarioAtivo.setValor(rs.getBigDecimal(3));
                precoDiarioAtivo.setIdAtivo(rs.getInt(4));
            }
            rs.close();
            ps.close();
            conn.close();
            return precoDiarioAtivo;
        } catch (SQLException e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return precoDiarioAtivo;
    }

    public Boolean createPrecoDiarioAtivo(PrecoDiarioAtivo precoDiarioAtivo) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("INSERT INTO TB_PRECO_DIARIO_ATIVO (DT_ATIVO, DEC_VALOR, ID_ATIVO) VALUES (?,?,?)");
            cs.setDate(1, Date.valueOf(precoDiarioAtivo.getData()));
            cs.setBigDecimal(2, precoDiarioAtivo.getValor());
            cs.setInt(3, precoDiarioAtivo.getIdAtivo());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean updatePrecoDiarioAtivo(PrecoDiarioAtivo precoDiarioAtivo) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("UPDATE TB_PRECO_DIARIO_ATIVO " +
                    "SET DT_ATIVO = ?," +
                    "    DEC_VALOR = ?," +
                    "    ID_ATIVO = ? " +
                    "WHERE ID_PRECO = ?");

            cs.setDate(1, Date.valueOf(precoDiarioAtivo.getData()));
            cs.setBigDecimal(2, precoDiarioAtivo.getValor());
            cs.setInt(3, precoDiarioAtivo.getIdAtivo());
            cs.setInt(4, precoDiarioAtivo.getIdPreco());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean deletePrecoDiarioAtivo(Integer id) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("DELETE FROM TB_PRECO_DIARIO_ATIVO WHERE ID_PRECO = ?");
            cs.setInt(1, id);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    private static void finallyConnection(Connection conn, ResultSet rs, PreparedStatement ps, CallableStatement cs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                Logger.getLogger(PrecoDiarioAtivoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
