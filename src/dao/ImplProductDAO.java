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

import entities.Product;
import utils.Database;

public class ImplProductDAO implements IDao<Product> {

	private Connection connextion;
	private static final String ADD_QUERY = "INSERT INTO product (id, name, description , quantity, price, image, store_id) "
			+ "VALUES (null,?,?,?,?,?,?)";
	private static final String GET_BY_ID_QUERY = "SELECT * FROM product WHERE id=?";
	private static final String GET_ALL_QUERY = "SELECT * FROM product";
	private static final String DELETE_QUERY = "DELETE FROM product WHERE id =?";
	private static final String UPDATE_QUERY = "UPDATE product set name = ?,description = ?,quantity = ?,price = ?,image = ?,store_id=? where id = ?";

	@Override
	public Optional<Product> get(int id) {
		Product product = null;
		PreparedStatement pt;
		try {
			pt = connextion.prepareStatement(GET_BY_ID_QUERY);
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				String quantity = rs.getString("quantity");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				int storeId = rs.getInt("store_id");
				product = new Product.Builder(id).withName(name).withDiscription(description).withQuantity(quantity)
						.withPrice(price).withImage(image).withStoreId(storeId).build();

			}
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Optional.ofNullable(product);
	}

	@Override
	public List<Product> getAll() {
		PreparedStatement pt;
		List<Product> products = new ArrayList<>();
		try {
			pt = connextion.prepareStatement(GET_ALL_QUERY);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String quantity = rs.getString("quantity");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				int storeId = rs.getInt("store_id");
				products.add(new Product.Builder(id).withName(name).withDiscription(description).withQuantity(quantity)
						.withPrice(price).withImage(image).withStoreId(storeId).build());

			}
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}

	@Override
	public void save(Product product) {
		PreparedStatement pt = null;
		try {
			pt = connextion.prepareStatement(ADD_QUERY);
			pt.setString(1, product.getName());
			pt.setString(2, product.getDescription());
			pt.setString(3, product.getQuantity());
			pt.setDouble(4, product.getPrice());
			pt.setString(5, product.getImage());
			pt.setInt(6, product.getStoreId());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void update(Product product) {
		PreparedStatement pt = null;
		try {
			pt = connextion.prepareStatement(UPDATE_QUERY);
			pt.setInt(7, product.getId());
			pt.setString(1, product.getName());
			pt.setString(2, product.getDescription());
			pt.setString(3, product.getQuantity());
			pt.setDouble(4, product.getPrice());
			pt.setString(5, product.getImage());
			pt.setInt(6, product.getStoreId());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(Product product) {
		PreparedStatement pt;
		try {
			pt = connextion.prepareStatement(DELETE_QUERY);
			pt.setInt(1, product.getId());
			pt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ImplProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private ImplProductDAO() {
		this.connextion = Database.getInstance().getConnection();
	}

	private static final ImplProductDAO PRODUCT_DAO = new ImplProductDAO();

	public static ImplProductDAO getInstance() {
		return PRODUCT_DAO;
	}

}
