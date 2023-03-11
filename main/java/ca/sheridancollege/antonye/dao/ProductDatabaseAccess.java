package ca.sheridancollege.antonye.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.antonye.model.Product;

@Repository
public class ProductDatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public long addProduct(Product product) {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource() ;
	String insert="INSERT INTO product (productCode, productBrand, quantityInHand, unitPrice)"
	            +"VALUES (:productCode,:productBrand,:quantityInHand,:unitPrice);";
	namedParameters.addValue("productCode",product.getProductCode()) ;
	namedParameters.addValue("productBrand",product.getProductBrand());
	namedParameters.addValue("quantityInHand",product.getQuantityInHand());
	namedParameters.addValue("unitPrice",product.getUnitPrice());
	long rowsAffected = jdbc.update(insert, namedParameters);
	return rowsAffected;
	}
	
	public List<Product> selectProduct(){
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	String query="SELECT * FROM product";
	List<Product> product = jdbc.query (query, namedParameters,
	       new BeanPropertyRowMapper<Product> (Product.class)) ;
	return product;
	}
	
	
	public List<Product> selectProductByBrand(String productBrand) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query="SELECT * FROM product where productBrand = :productBrand";
			namedParameters.addValue("productBrand", productBrand ) ;
			List<Product> product = jdbc.query (query, namedParameters,
			               new BeanPropertyRowMapper<Product>(Product.class));
			return product;
			

	}
	
	public List<Product> selectProductByCode(String productCode) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query="SELECT * FROM product where productCode =:productCode";
			namedParameters.addValue("productCode", productCode ) ;
			List<Product> product = jdbc.query (query, namedParameters,
			               new BeanPropertyRowMapper<Product>(Product.class));
			return product;
			

	}
	public Product selectProductByProductCode(String productCode) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		Product product;
		String productByproductCode = "SELECT * FROM product where productCode=:productCode";
		namedParameters.addValue("productCode", productCode);
		List<Product> products =jdbc.query(productByproductCode, namedParameters,
		new BeanPropertyRowMapper<Product>(Product.class));
		if (products.size() > 0 ){
			product = products.get(0);
		}
		else {
			product = new Product();
		}
		return product;
		}
	
	public long updateProductByProductData(Product product) {
		MapSqlParameterSource namedParameters = new
		MapSqlParameterSource();
		String updateProduct =
		"update product Set productCode = :productCode, "
		+"productBrand =:productBrand, quantityInHand =:quantityInHand, unitPrice =:unitPrice "
		+ "where productCode = :productCode;";
		namedParameters.addValue("id", product.getId());
		namedParameters.addValue("productCode",product.getProductCode());
		namedParameters.addValue("productBrand", product.getProductBrand());
		namedParameters.addValue("quantityInHand",product.getQuantityInHand());
		namedParameters.addValue("unitPrice",product.getUnitPrice());
		int rowsUpdated = jdbc.update(updateProduct, namedParameters);
		return rowsUpdated;
		}

	public long deleteProductByProductCode(String productCode) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String deleteByproductCode = "DELETE FROM product where productCode =:productCode";
		namedParameters.addValue("productCode", productCode);
		int rowsDeleted =jdbc.update(deleteByproductCode, namedParameters);
		return rowsDeleted;
	}

	public List<Product> selectProductAboveUnitPrice(double unitPrice) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="SELECT * FROM product where unitPrice > :unitPrice";
		namedParameters.addValue("unitPrice", unitPrice ) ;
		List<Product> product = jdbc.query (query, namedParameters,
		               new BeanPropertyRowMapper<Product>(Product.class));
		return product;
	}
	
}
