package br.com.senac.quarkus.projetofullstack.repository;

import br.com.senac.quarkus.projetofullstack.entity.Investidor;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class InvestidorRepository {

    @ConfigProperty(name="quarkus.datasource.url")
    String url;
    @ConfigProperty(name="quarkus.datasource.username")
    String username;
    @ConfigProperty(name="quarkus.datasource.password")
    String password;

    public List<Investidor> getAllInvestidores() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Investidor> investidores = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_INVESTIDOR, STR_NOME, STR_ENDERECO, STR_EMAIL, STR_TELEFONE, STR_CPF, DT_NASCIMENTO, DEC_SALDO_DISPONIVEL FROM TB_INVESTIDOR");
            rs = ps.executeQuery();
            while (rs.next()) {
                Investidor investidor = new Investidor();

                investidor.setIdInvestidor(rs.getInt(1));
                investidor.setNome(rs.getString(2));
                investidor.setEndereco(rs.getString(3));
                investidor.setEmail(rs.getString(4));
                investidor.setTelefone(rs.getString(5));
                investidor.setCpf(rs.getString(6));
                investidor.setDataNascimento(rs.getDate(7).toLocalDate());
                investidor.setSaldoDisponivel(rs.getBigDecimal(8));

                investidores.add(investidor);
            }
            rs.close();
            ps.close();
            conn.close();
            return investidores;
        } catch (SQLException e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return investidores;
    }

    public Investidor getInvestidorById(Integer id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Investidor investidor = new Investidor();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_INVESTIDOR, STR_NOME, STR_ENDERECO, STR_EMAIL, STR_TELEFONE, STR_CPF, DT_NASCIMENTO, DEC_SALDO_DISPONIVEL FROM TB_INVESTIDOR WHERE ID_INVESTIDOR = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                investidor.setIdInvestidor(rs.getInt(1));
                investidor.setNome(rs.getString(2));
                investidor.setEndereco(rs.getString(3));
                investidor.setEmail(rs.getString(4));
                investidor.setTelefone(rs.getString(5));
                investidor.setCpf(rs.getString(6));
                investidor.setDataNascimento(rs.getDate(7).toLocalDate());
                investidor.setSaldoDisponivel(rs.getBigDecimal(8));
            }
            rs.close();
            ps.close();
            conn.close();
            return investidor;
        } catch (SQLException e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return investidor;
    }

    public Boolean createInvestidor(Investidor investidor) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("INSERT INTO TB_INVESTIDOR (STR_NOME, STR_ENDERECO, STR_EMAIL, STR_TELEFONE, STR_CPF, DT_NASCIMENTO, DEC_SALDO_DISPONIVEL) VALUES (?,?,?,?,?,?,?)");
            cs.setString(1, investidor.getNome());
            cs.setString(2, investidor.getEndereco());
            cs.setString(3, investidor.getEmail());
            cs.setString(4, investidor.getTelefone());
            cs.setString(5, investidor.getCpf());
            cs.setDate(6, Date.valueOf(investidor.getDataNascimento()));
            cs.setBigDecimal(7, investidor.getSaldoDisponivel());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean updateInvestidor(Investidor investidor) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("UPDATE TB_INVESTIDOR " +
                    "SET STR_NOME = ?," +
                    "    STR_ENDERECO = ?," +
                    "    STR_EMAIL = ?," +
                    "    STR_TELEFONE = ?," +
                    "    STR_CPF = ?," +
                    "    DT_NASCIMENTO = ?," +
                    "    DEC_SALDO_DISPONIVEL = ? " +
                    "WHERE ID_INVESTIDOR = ?");

            cs.setString(1, investidor.getNome());
            cs.setString(2, investidor.getEndereco());
            cs.setString(3, investidor.getEmail());
            cs.setString(4, investidor.getTelefone());
            cs.setString(5, investidor.getCpf());
            cs.setDate(6, Date.valueOf(investidor.getDataNascimento()));
            cs.setBigDecimal(7, investidor.getSaldoDisponivel());

            cs.setInt(8, investidor.getIdInvestidor());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean deleteInvestidor(Integer id) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("DELETE FROM TB_INVESTIDOR WHERE ID_INVESTIDOR = ?");
            cs.setInt(1, id);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
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
                Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                Logger.getLogger(InvestidorRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
