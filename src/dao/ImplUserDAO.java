package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.User;
import utils.Database;

public class ImplUserDAO implements IDao<User> {

	private Connection connextion;
	private static final String ADD_QUERY = "INSERT INTO user (id,login,roles,nom, prenom , cin,sexe,adresse,num_tel,email, password,is_verified) "
			+ "VALUES (null,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
	private static final String GET_ALL_QUERY = "SELECT * FROM user";
	private static final String DELETE_QUERY = "DELETE FROM user WHERE id =?";
	private static final String UPDATE_QUERY = "UPDATE user set  login = ?, roles = ?, nom = ?, prenom = ?, cin = ?, sexe = ? adresse = ?,num_tel = ?,email = ?,password = ?,is_verified = ? where id = ?";;

	@Override
	public Optional<User> get(int id) {
		User representantPara = null;
		PreparedStatement pt;
		try {
			pt = connextion.prepareStatement(GET_BY_ID_QUERY);
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String login = rs.getString("login");
				String role = rs.getString("roles");
				String firstName = rs.getString("nom");
				String lastName = rs.getString("prenom");
				String cin = rs.getString("cin");
				String gender = rs.getString("sexe");
				String address = rs.getString("adresse");
				String phoneNumber = rs.getString("num_tel");
				String email = rs.getString("email");
				String password = rs.getString("password");
				boolean verified = rs.getBoolean("is_verified");
				representantPara = new User.Builder<>(firstName, lastName).withCIN(cin).withPassowrd(password)
						.withRole(role).withAddress(address).withEmail(email).withLogin(login)
						.withPhoneNumber(phoneNumber).withGender(gender).isVerified(verified).build();

			}
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Optional.ofNullable(representantPara);
	}

	@Override
	public List<User> getAll() {
		PreparedStatement pt;
		List<User> users = new ArrayList<>();
		try {
			pt = connextion.prepareStatement(GET_ALL_QUERY);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String login = rs.getString("login");
				String role = rs.getString("roles");
				String firstName = rs.getString("nom");
				String lastName = rs.getString("prenom");
				String cin = rs.getString("cin");
				String gender = rs.getString("sexe");
				String address = rs.getString("adresse");
				String phoneNumber = rs.getString("num_tel");
				String email = rs.getString("email");
				String password = rs.getString("password");
				boolean verified = rs.getBoolean("is_verified");

				users.add(new User.Builder<>(firstName, lastName).withCIN(cin).withPassowrd(password)
						.withRole(role).withAddress(address).withEmail(email).withLogin(login)
						.withPhoneNumber(phoneNumber).withGender(gender).isVerified(verified).build());

			}
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return users;
	}

	@Override
	public void save(User user) {
		PreparedStatement pt = null;
		try {
			pt = connextion.prepareStatement(ADD_QUERY);
			pt.setString(1, user.getLogin());
			pt.setString(2, user.getRole());
			pt.setString(3, user.getFirstName());
			pt.setString(4, user.getLastName());
			pt.setString(5, user.getCin());
			pt.setString(6, user.getGender());
			pt.setString(7, user.getAddress());
			pt.setString(8, user.getPhoneNumber());
			pt.setString(9, user.getEmail());
			pt.setString(10, user.getPassword());
			pt.setBoolean(11, user.isVerified());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void update(User user) {
		PreparedStatement pt = null;
		try {
			pt = connextion.prepareStatement(UPDATE_QUERY);
			pt.setInt(12, user.getId());
			pt.setString(1, user.getLogin());
			pt.setString(2, user.getRole());
			pt.setString(3, user.getFirstName());
			pt.setString(4, user.getLastName());
			pt.setString(5, user.getCin());
			pt.setString(6, user.getGender());
			pt.setString(7, user.getAddress());
			pt.setString(8, user.getPhoneNumber());
			pt.setString(9, user.getEmail());
			pt.setString(10, user.getPassword());
			pt.setBoolean(11, user.isVerified());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(User user) {
		PreparedStatement pt;
		try {
			pt = connextion.prepareStatement(DELETE_QUERY);
			pt.setInt(1, user.getId());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private ImplUserDAO() {
		this.connextion = Database.getInstance().getConnection();
	}

	private static final ImplUserDAO REPRESENTANT_PARA_DAO = new ImplUserDAO();

	public static ImplUserDAO getInstance() {
		return REPRESENTANT_PARA_DAO;
	}

}
