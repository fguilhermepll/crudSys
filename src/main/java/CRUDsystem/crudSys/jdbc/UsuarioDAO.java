package CRUDsystem.crudSys.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CRUDsystem.crudSys.model.Usuario;

public class UsuarioDAO {
	
	private Connection con = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usu){
		String sql = "insert into usuario (nome, login, senha) values (?, ?, ?)";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Usuario usu){
		String sql = "update usuario set nome=?, login=?, senha=? where id=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void excluir(Usuario usu){
		String sql = "delete from usuario where id=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, usu.getId());
			
			preparador.execute();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void salvar(Usuario usu){
		if(usu.getId() != null && usu.getId() != 0){
			alterar(usu);
		} else{
			cadastrar(usu);
		}
	}
	
	public Usuario buscarPorId(Integer id){
		String sql = "select * from usuario where id=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()){
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				
				return usu;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Usuario> buscarTodos(){
		String sql = "select * from usuario";
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				lista.add(usu);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Usuario autenticar(Usuario usuConsulta){
		String sql = "select * from usuario where login=? and senha=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery(); 

			if(resultado.next()){
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));

				return usu;
			}else{
				return null;
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}

}
