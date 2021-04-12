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

import entities.Store;
import utils.Database;

public class ImplStoreDAO implements IDao<Store> {

	private Connection connextion;
	private static final String ADD_QUERY = "INSERT INTO store (id, name, phone , email) " + "VALUES (null,?,?,?)";
	private static final String GET_BY_ID_QUERY = "SELECT * FROM store WHERE id=?";
	private static final String GET_ALL_QUERY = "SELECT * FROM store";
	private static final String DELETE_QUERY = "DELETE FROM store WHERE id =?";
	private static final String UPDATE_QUERY = "UPDATE store set name = ?,phone = ?,email = ? where id = ?";

	@Override
	public Optional<Store> get(int id) {
		Store store = null;
		PreparedStatement pt;
		try {
			pt = connextion.prepareStatement(GET_BY_ID_QUERY);
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				store = new Store(name, phone, email);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Optional.ofNullable(store);
	}

	@Override
	public List<Store> getAll() {
		PreparedStatement pt;
		List<Store> products = new ArrayList<>();
		try {
			pt = connextion.prepareStatement(GET_ALL_QUERY);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				products.add(new Store(name, phone, email));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}

	@Override
	public void save(Store store) {
		PreparedStatement pt = null;
		try {
			pt = connextion.prepareStatement(ADD_QUERY);
			pt.setString(1, store.getName());
			pt.setString(2, store.getPhone());
			pt.setString(3, store.getEmail());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void update(Store store) {
		PreparedStatement pt = null;
		try {
			pt = connextion.prepareStatement(UPDATE_QUERY);
			pt.setInt(4, store.getId());
			pt.setString(1, store.getName());
			pt.setString(2, store.getPhone());
			pt.setString(3, store.getEmail());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(Store store) {
		PreparedStatement pt;
		try {
			pt = connextion.prepareStatement(DELETE_QUERY);
			pt.setInt(1, store.getId());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private ImplStoreDAO() {
		this.connextion = Database.getInstance().getConnection();
	}

	private static final ImplStoreDAO STORE_DAO = new ImplStoreDAO();

	public static ImplStoreDAO getInstance() {
		return STORE_DAO;
	}

}
