package br.com.senac.quarkus.projetofullstack.repository;

import br.com.senac.quarkus.projetofullstack.entity.AtivoFinanceiro;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AtivoFinanceiroRepository {

    @ConfigProperty(name = "quarkus.datasource.url")
    String url;
    @ConfigProperty(name = "quarkus.datasource.username")
    String username;
    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    public List<AtivoFinanceiro> getAllAtivosFinanceiros() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<AtivoFinanceiro> ativosFinanceiros = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_ATIVO, STR_NOME_ATIVO, STR_TIPO_ATIVO, STR_DESCRICAO, DEC_VALOR_ATUAL, DEC_VALOR_MERCADO, DEC_RENDIMENTO, STR_RISCO, STR_SETOR FROM TB_ATIVO_FINANCEIRO");
            rs = ps.executeQuery();
            while (rs.next()) {
                AtivoFinanceiro ativo = new AtivoFinanceiro();

                ativo.setIdAtivo(rs.getInt(1));
                ativo.setNomeAtivo(rs.getString(2));
                ativo.setTipoAtivo(rs.getString(3));
                ativo.setDescricao(rs.getString(4));
                ativo.setValorAtual(rs.getBigDecimal(5));
                ativo.setValorMercado(rs.getBigDecimal(6));
                ativo.setRendimento(rs.getBigDecimal(7));
                ativo.setRisco(rs.getString(8));
                ativo.setSetor(rs.getString(9));

                ativosFinanceiros.add(ativo);
            }
            rs.close();
            ps.close();
            conn.close();
            return ativosFinanceiros;
        } catch (SQLException e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return ativosFinanceiros;
    }

    public AtivoFinanceiro getAtivoFinanceiroById(Integer id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        AtivoFinanceiro ativo = new AtivoFinanceiro();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_ATIVO, STR_NOME_ATIVO, STR_TIPO_ATIVO, STR_DESCRICAO, DEC_VALOR_ATUAL, DEC_VALOR_MERCADO, DEC_RENDIMENTO, STR_RISCO, STR_SETOR FROM TB_ATIVO_FINANCEIRO WHERE ID_ATIVO = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ativo.setIdAtivo(rs.getInt(1));
                ativo.setNomeAtivo(rs.getString(2));
                ativo.setTipoAtivo(rs.getString(3));
                ativo.setDescricao(rs.getString(4));
                ativo.setValorAtual(rs.getBigDecimal(5));
                ativo.setValorMercado(rs.getBigDecimal(6));
                ativo.setRendimento(rs.getBigDecimal(7));
                ativo.setRisco(rs.getString(8));
                ativo.setSetor(rs.getString(9));
            }
            rs.close();
            ps.close();
            conn.close();
            return ativo;
        } catch (SQLException e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return ativo;
    }

    public Boolean createAtivoFinanceiro(AtivoFinanceiro ativo) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("INSERT INTO TB_ATIVO_FINANCEIRO (STR_NOME_ATIVO, STR_TIPO_ATIVO, STR_DESCRICAO, DEC_VALOR_ATUAL, DEC_VALOR_MERCADO, DEC_RENDIMENTO, STR_RISCO, STR_SETOR) VALUES (?,?,?,?,?,?,?,?)");
            cs.setString(1, ativo.getNomeAtivo());
            cs.setString(2, ativo.getTipoAtivo());
            cs.setString(3, ativo.getDescricao());
            cs.setBigDecimal(4, ativo.getValorAtual());
            cs.setBigDecimal(5, ativo.getValorMercado());
            cs.setBigDecimal(6, ativo.getRendimento());
            cs.setString(7, ativo.getRisco());
            cs.setString(8, ativo.getSetor());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean updateAtivoFinanceiro(AtivoFinanceiro ativo) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("UPDATE TB_ATIVO_FINANCEIRO " +
                    "SET STR_NOME_ATIVO = ?," +
                    "    STR_TIPO_ATIVO = ?," +
                    "    STR_DESCRICAO = ?," +
                    "    DEC_VALOR_ATUAL = ?," +
                    "    DEC_VALOR_MERCADO = ?," +
                    "    DEC_RENDIMENTO = ?," +
                    "    STR_RISCO = ?," +
                    "    STR_SETOR = ? " +
                    "WHERE ID_ATIVO = ?");

            cs.setString(1, ativo.getNomeAtivo());
            cs.setString(2, ativo.getTipoAtivo());
            cs.setString(3, ativo.getDescricao());
            cs.setBigDecimal(4, ativo.getValorAtual());
            cs.setBigDecimal(5, ativo.getValorMercado());
            cs.setBigDecimal(6, ativo.getRendimento());
            cs.setString(7, ativo.getRisco());
            cs.setString(8, ativo.getSetor());
            cs.setInt(9, ativo.getIdAtivo());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean deleteAtivoFinanceiro(Integer id) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("DELETE FROM TB_ATIVO_FINANCEIRO WHERE ID_ATIVO = ?");
            cs.setInt(1, id);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
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
                Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                Logger.getLogger(AtivoFinanceiroRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
