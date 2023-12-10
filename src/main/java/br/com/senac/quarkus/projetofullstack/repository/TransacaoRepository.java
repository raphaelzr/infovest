package br.com.senac.quarkus.projetofullstack.repository;

import br.com.senac.quarkus.projetofullstack.entity.Transacao;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class TransacaoRepository {

    @ConfigProperty(name = "quarkus.datasource.url")
    String url;
    @ConfigProperty(name = "quarkus.datasource.username")
    String username;
    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    public List<Transacao> getAllTransacoes() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT id_transacao, str_tipo_transacao, int_quantidade, dt_hr_transacao, dec_valor_total_transacao, str_status_transacao, id_carteira, id_ativo, id_preco FROM TB_TRANSACAO");
            rs = ps.executeQuery();
            while (rs.next()) {
                Transacao transacao = new Transacao();

                transacao.setIdTransacao(rs.getInt(1));
                transacao.setTipoTransacao(rs.getString(2));
                transacao.setQuantidade(rs.getInt(3));
                transacao.setDataHora(rs.getTimestamp(4).toLocalDateTime());
                transacao.setValorTotalTransacao(rs.getBigDecimal(5));
                transacao.setStatusTransacao(rs.getString(6));
                transacao.setIdCarteira(rs.getInt(7));
                transacao.setIdAtivo(rs.getInt(8));
                transacao.setIdPreco(rs.getInt(9));

                transacoes.add(transacao);
            }
            rs.close();
            ps.close();
            conn.close();
            return transacoes;
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return transacoes;
    }

    public Transacao getTransacaoById(Integer id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Transacao transacao = new Transacao();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT id_transacao, str_tipo_transacao, int_quantidade, dt_hr_transacao, dec_valor_total_transacao, str_status_transacao, id_carteira, id_ativo, id_preco FROM TB_TRANSACAO WHERE id_transacao = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                transacao.setIdTransacao(rs.getInt(1));
                transacao.setTipoTransacao(rs.getString(2));
                transacao.setQuantidade(rs.getInt(3));
                transacao.setDataHora(rs.getTimestamp(4).toLocalDateTime());
                transacao.setValorTotalTransacao(rs.getBigDecimal(5));
                transacao.setStatusTransacao(rs.getString(6));
                transacao.setIdCarteira(rs.getInt(7));
                transacao.setIdAtivo(rs.getInt(8));
                transacao.setIdPreco(rs.getInt(9));
            }
            rs.close();
            ps.close();
            conn.close();
            return transacao;
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return transacao;
    }

    public Boolean createTransacao(Transacao transacao) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("INSERT INTO TB_TRANSACAO (str_tipo_transacao, int_quantidade, dt_hr_transacao, dec_valor_total_transacao, str_status_transacao, id_carteira, id_ativo, id_preco) VALUES (?,?,?,?,?,?,?,?)");
            cs.setString(1, transacao.getTipoTransacao());
            cs.setInt(2, transacao.getQuantidade());
            cs.setTimestamp(3, Timestamp.valueOf(transacao.getDataHora()));
            cs.setBigDecimal(4, transacao.getValorTotalTransacao());
            cs.setString(5, transacao.getStatusTransacao());
            cs.setInt(6, transacao.getIdCarteira());
            cs.setInt(7, transacao.getIdAtivo());
            cs.setInt(8, transacao.getIdPreco());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean updateTransacao(Transacao transacao) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("UPDATE TB_TRANSACAO " +
                    "SET str_tipo_transacao = ?," +
                    "    int_quantidade = ?," +
                    "    dt_hr_transacao = ?," +
                    "    dec_valor_total_transacao = ?," +
                    "    str_status_transacao = ?," +
                    "    id_carteira = ?," +
                    "    id_ativo = ?," +
                    "    id_preco = ? " +
                    "WHERE id_transacao = ?;");

            cs.setString(1, transacao.getTipoTransacao());
            cs.setInt(2, transacao.getQuantidade());
            cs.setTimestamp(3, Timestamp.valueOf(transacao.getDataHora()));
            cs.setBigDecimal(4, transacao.getValorTotalTransacao());
            cs.setString(5, transacao.getStatusTransacao());
            cs.setInt(6, transacao.getIdCarteira());
            cs.setInt(7, transacao.getIdAtivo());
            cs.setInt(8, transacao.getIdPreco());
            cs.setInt(9, transacao.getIdTransacao());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean deleteTransacao(Integer id) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("DELETE FROM TB_TRANSACAO WHERE id_transacao = ?");
            cs.setInt(1, id);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }
    public List<Transacao> getAllTransacoesByCarteiraAndAtivo(Integer carteira, Integer ativo) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT id_transacao, str_tipo_transacao, int_quantidade, dt_hr_transacao, dec_valor_total_transacao, str_status_transacao, id_carteira, id_ativo, id_preco FROM TB_TRANSACAO where id_carteira = ? and id_ativo = ?");

            ps.setInt(1, carteira);
            ps.setInt(2, ativo);

            rs = ps.executeQuery();
            while (rs.next()) {
                Transacao transacao = new Transacao();

                transacao.setIdTransacao(rs.getInt(1));
                transacao.setTipoTransacao(rs.getString(2));
                transacao.setQuantidade(rs.getInt(3));
                transacao.setDataHora(rs.getTimestamp(4).toLocalDateTime());
                transacao.setValorTotalTransacao(rs.getBigDecimal(5));
                transacao.setStatusTransacao(rs.getString(6));
                transacao.setIdCarteira(rs.getInt(7));
                transacao.setIdAtivo(rs.getInt(8));
                transacao.setIdPreco(rs.getInt(9));

                transacoes.add(transacao);
            }
            rs.close();
            ps.close();
            conn.close();
            return transacoes;
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return transacoes;
    }

    public List<Transacao> getAllTransacoesByCarteira(Integer carteira) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Transacao> transacoes = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT id_transacao, str_tipo_transacao, int_quantidade, dt_hr_transacao, dec_valor_total_transacao, str_status_transacao, id_carteira, id_ativo, id_preco FROM TB_TRANSACAO where id_carteira = ? ");

            ps.setInt(1, carteira);

            rs = ps.executeQuery();
            while (rs.next()) {
                Transacao transacao = new Transacao();

                transacao.setIdTransacao(rs.getInt(1));
                transacao.setTipoTransacao(rs.getString(2));
                transacao.setQuantidade(rs.getInt(3));
                transacao.setDataHora(rs.getTimestamp(4).toLocalDateTime());
                transacao.setValorTotalTransacao(rs.getBigDecimal(5));
                transacao.setStatusTransacao(rs.getString(6));
                transacao.setIdCarteira(rs.getInt(7));
                transacao.setIdAtivo(rs.getInt(8));
                transacao.setIdPreco(rs.getInt(9));

                transacoes.add(transacao);
            }
            rs.close();
            ps.close();
            conn.close();
            return transacoes;
        } catch (SQLException e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return transacoes;
    }

    private static void finallyConnection(Connection conn, ResultSet rs, PreparedStatement ps, CallableStatement cs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                Logger.getLogger(TransacaoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
