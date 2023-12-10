package br.com.senac.quarkus.projetofullstack.repository;

import br.com.senac.quarkus.projetofullstack.entity.CarteiraInvestimentos;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class CarteiraInvestimentosRepository {

    @ConfigProperty(name = "quarkus.datasource.url")
    String url;
    @ConfigProperty(name = "quarkus.datasource.username")
    String username;
    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    public List<CarteiraInvestimentos> getAllCarteirasInvestimentos() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<CarteiraInvestimentos> carteirasInvestimentos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_CARTEIRA, STR_NOME_CARTEIRA, DT_CARTEIRA, DEC_DESEMPENHO_TOTAL, DEC_VALOR_TOTAL_INVESTIDO, DEC_VALOR_TOTAL_ATUAL, DEC_RENTABILIDADE, ID_INVESTIDOR FROM TB_CARTEIRA_INVESTIMENTOS");
            rs = ps.executeQuery();
            while (rs.next()) {
                CarteiraInvestimentos carteira = new CarteiraInvestimentos();

                carteira.setIdCarteira(rs.getInt(1));
                carteira.setNomeCarteira(rs.getString(2));
                carteira.setDataCriacao(rs.getDate(3).toLocalDate());
                carteira.setDesempenhoTotal(rs.getBigDecimal(4));
                carteira.setValorTotalInvestido(rs.getBigDecimal(5));
                carteira.setValorTotalAtual(rs.getBigDecimal(6));
                carteira.setRentabilidade(rs.getBigDecimal(7));
                carteira.setIdInvestidor(rs.getInt(8));

                carteirasInvestimentos.add(carteira);
            }
            rs.close();
            ps.close();
            conn.close();
            return carteirasInvestimentos;
        } catch (SQLException e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return carteirasInvestimentos;
    }

    public CarteiraInvestimentos getCarteiraInvestimentosById(Integer id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        CarteiraInvestimentos carteira = new CarteiraInvestimentos();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement("SELECT ID_CARTEIRA, STR_NOME_CARTEIRA, DT_CARTEIRA, DEC_DESEMPENHO_TOTAL, DEC_VALOR_TOTAL_INVESTIDO, DEC_VALOR_TOTAL_ATUAL, DEC_RENTABILIDADE, ID_INVESTIDOR FROM TB_CARTEIRA_INVESTIMENTOS WHERE ID_CARTEIRA = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                carteira.setIdCarteira(rs.getInt(1));
                carteira.setNomeCarteira(rs.getString(2));
                carteira.setDataCriacao(rs.getDate(3).toLocalDate());
                carteira.setDesempenhoTotal(rs.getBigDecimal(4));
                carteira.setValorTotalInvestido(rs.getBigDecimal(5));
                carteira.setValorTotalAtual(rs.getBigDecimal(6));
                carteira.setRentabilidade(rs.getBigDecimal(7));
                carteira.setIdInvestidor(rs.getInt(8));
            }
            rs.close();
            ps.close();
            conn.close();
            return carteira;
        } catch (SQLException e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, rs, ps, null);
        }
        return carteira;
    }

    public Boolean createCarteiraInvestimentos(CarteiraInvestimentos carteira) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("INSERT INTO TB_CARTEIRA_INVESTIMENTOS (STR_NOME_CARTEIRA, DT_CARTEIRA, DEC_DESEMPENHO_TOTAL, DEC_VALOR_TOTAL_INVESTIDO, DEC_VALOR_TOTAL_ATUAL, DEC_RENTABILIDADE, ID_INVESTIDOR) VALUES (?,?,?,?,?,?,?)");
            cs.setString(1, carteira.getNomeCarteira());
            cs.setDate(2, Date.valueOf(carteira.getDataCriacao()));
            cs.setBigDecimal(3, carteira.getDesempenhoTotal());
            cs.setBigDecimal(4, carteira.getValorTotalInvestido());
            cs.setBigDecimal(5, carteira.getValorTotalAtual());
            cs.setBigDecimal(6, carteira.getRentabilidade());
            cs.setInt(7, carteira.getIdInvestidor());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean updateCarteiraInvestimentos(CarteiraInvestimentos carteira) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("UPDATE TB_CARTEIRA_INVESTIMENTOS " +
                    "SET STR_NOME_CARTEIRA = ?," +
                    "    DT_CARTEIRA = ?," +
                    "    DEC_DESEMPENHO_TOTAL = ?," +
                    "    DEC_VALOR_TOTAL_INVESTIDO = ?," +
                    "    DEC_VALOR_TOTAL_ATUAL = ?," +
                    "    DEC_RENTABILIDADE = ?," +
                    "    ID_INVESTIDOR = ? " +
                    "WHERE ID_CARTEIRA = ?;");

            cs.setString(1, carteira.getNomeCarteira());
            cs.setDate(2, Date.valueOf(carteira.getDataCriacao()));
            cs.setBigDecimal(3, carteira.getDesempenhoTotal());
            cs.setBigDecimal(4, carteira.getValorTotalInvestido());
            cs.setBigDecimal(5, carteira.getValorTotalAtual());
            cs.setBigDecimal(6, carteira.getRentabilidade());
            cs.setInt(7, carteira.getIdInvestidor());
            cs.setInt(8, carteira.getIdCarteira());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            finallyConnection(conn, null, null, cs);
        }
        return result;
    }

    public Boolean deleteCarteiraInvestimentos(Integer id) {
        Connection conn = null;
        CallableStatement cs = null;
        Boolean result = Boolean.FALSE;

        try {
            conn = DriverManager.getConnection(url, username, password);
            cs = conn.prepareCall("DELETE FROM TB_CARTEIRA_INVESTIMENTOS WHERE ID_CARTEIRA = ?");
            cs.setInt(1, id);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                result = Boolean.TRUE;
            }

            cs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
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
                Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                Logger.getLogger(CarteiraInvestimentosRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
