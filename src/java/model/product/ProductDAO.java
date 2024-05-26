/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

import utils.DBContext;
import java.util.List;
import java.util.Optional;
import model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.brand.Brand;
import model.brand.BrandDAO;
import model.review.Review;
import model.type.Type;
import model.type.TypeDAO;
/**
 *
 * @author asus
 */
public class ProductDAO extends DBContext implements DAO<Product> {

    @Override
    public Optional<Product> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
    @Override
    public List<Product> getAll() {
      List<Product> list = new ArrayList<>();
      String sql = "select * from [products]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;  
    }
    
    public List<Product> getRelated(int brand1,int type1,int id){
      List<Product> list = new ArrayList<>();
      String sql="";
             sql = "select top 4 * from [products] where (brand= ? or type= ?) and id!= ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brand1);
            st.setInt(2, type1);
            st.setInt(3, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;  
    }
    public void insertproduct(String title,double inprice,double outprice,int stock,int nokc,String material,String suitability,float density,float discount,String type,String brand, String image) {
        TypeDAO t = new TypeDAO();
        BrandDAO b = new BrandDAO();
        if(t.checktype(type)== false){
            String sql = "insert into types values (?)";
            try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }
System.out.println("ko");
return;
        }
        if(b.checkbrand(brand)== false){
            String sql = "insert into brands values (?)";
            try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, brand);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }

System.out.println("ko");
return;
        }
        int typeid = t.findtypename(type);
        int brandid= b.findbrandname(brand);
        
        String sql="insert into products values (?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setDouble(2, inprice);
            st.setInt(3, stock);
            st.setDouble(4, outprice);
            st.setInt(5, typeid);
            st.setInt(6, brandid);
            st.setString(8, material);
            st.setString(9, suitability);
            st.setFloat(10, density);
            st.setFloat(11, discount);
            st.setInt(7, nokc);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }
        if(!image.isBlank()){
        String sql1 = "select max(id)as id  from products ";
        int id=0;
        try {
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            id= rs.getInt("id");
            }
         String sql2= "insert into product_imgs values (?,?)";
        try{
            st = connection.prepareStatement(sql2);
            st.setInt(2, id);
            st.setString(1, image);
            
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }    
            
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        } 
    }
    public void addImage(int id,String image){
      String sql2= "insert into product_imgs values (?,?)";
        try{
          PreparedStatement   st = connection.prepareStatement(sql2);
            st.setInt(2, id);
            st.setString(1, image);
            
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }    
            
               
    }
    
    
    
    
    
    
     public void updateproduct(String title,double inprice,double outprice,int stock,int nokc,String material,String suitability,float density,float discount,String type,String brand,int productid,String img,String product_img) {
        TypeDAO t = new TypeDAO();
        BrandDAO b = new BrandDAO();
        if(t.checktype(type)== false){
            String sql = "insert into types values (?)";
            try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }
          
        }
        if(b.checkbrand(brand)== false){
            String sql = "insert into brands values (?)";
            try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, brand);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }

        }
        int typeid = t.findtypename(type);
        int brandid= b.findbrandname(brand);
        
         String sql = "UPDATE [dbo].[products]\n"
                 + "   SET [title] = ?\n"
                 + "      ,[inprice] = ?\n"
                 + "      ,[stock] = ?\n"
                 + "      ,[outprice] = ?\n"
                 + "      ,[type] = ?\n"
                 + "      ,[brand] = ?\n"
                 + "      ,[nokc] = ?\n"
                 + "      ,[material] = ?\n"
                 + "      ,[suitability] = ?\n"
                 + "      ,[density] = ?\n"
                 + "      ,[discount] = ?\n"
                 + " WHERE id = ? ";
         try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setDouble(2, inprice);
            st.setInt(3, stock);
            st.setDouble(4, outprice);
            st.setInt(5, typeid);
            st.setInt(6, brandid);
            st.setInt(7, nokc);
            st.setString(8, material);
            st.setString(9, suitability);
            st.setFloat(10, density);
            st.setFloat(11, discount);
            st.setInt(12, productid);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }
        
        if(!product_img.isBlank()){
         String sql2= "update product_imgs set href=? where href =? and product_id =?";
        try{
        PreparedStatement   st = connection.prepareStatement(sql2);
            st.setString(2, img);
            st.setString(1, product_img);
            st.setInt(3, productid);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }    
        }   
            
        
        
       
    }
    @Override
    public void insert(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Product t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public Product getProduct(int id) {
        
        String sql = "select * from [products] where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(id);
                product.setType(type);
                Brand brand = getPBrand(id);
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
     
     public List<Product> getProductByName(String title) {
         List<Product> list = new ArrayList<>();
        String sql = "select * from products where title like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + title + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(product.getId());
                product.setType(type);
                Brand brand = getPBrand(product.getId());
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
      public List<Product> getProductByType(String type1) {
         List<Product> list = new ArrayList<>();
        String sql = "select p.* from products p join types t on p.type=t.id \n" +
"where t.name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + type1 + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(product.getId());
                product.setType(type);
                Brand brand = getPBrand(product.getId());
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
       public List<Product> getProductBybrand(String title) {
         List<Product> list = new ArrayList<>();
        String sql = "select p.* from products p join brands b on p.brand=b.id \n" +
"where b.name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + title + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(product.getId());
                product.setType(type);
                Brand brand = getPBrand(product.getId());
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
      private List<Review> getReviewsListForProduct(int productId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE product_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Review review = new Review();
                    review.setId(resultSet.getInt("id"));
                    review.setCustomer_id(resultSet.getInt("customer_id"));
                    review.setProduct_id(resultSet.getInt("product_id"));
                    review.setReviewPoint(resultSet.getInt("review"));
                    review.setReviewDate(resultSet.getDate("reviewDate"));
                    review.setDetail(resultSet.getString("detail"));
                    reviews.add(review);
                }
            }
        }

        return reviews;
    }
     
       private List<String> getImagesListForProduct(int productId) throws SQLException {
        List<String> images = new ArrayList<>();
        String sql = "SELECT * FROM product_imgs WHERE product_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    images.add(resultSet.getString("href"));
                }
            }
        }

        return images;
    }
       
       private Type getPType(int productId) throws SQLException{
           Type type = new Type();
           String sql="SELECT t.id,t.name FROM types t join products p on p.type= t.id where p.id= ?";
           try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery(); 
            while(rs.next()){
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
            }
           }catch (SQLException e) {
            System.out.println(e);          
           }
     return null;
}
 
        private Brand getPBrand(int productId) throws SQLException {
        Brand brand = new Brand();
        String sql = "SELECT b.id,b.name FROM brands b join products p on p.brand= b.id where p.id = ?";                              
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                return brand;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
}
 
    public List<Product> getBestSeller() {
        List<Product> list = new ArrayList<>();
        String sql = " with T as (\n"
                + "select top 6 product_id,sum(amount) as buyingnumber from order_details \n"
                + "group by product_id \n"
                + "order by buyingnumber DESC)\n"
                + "select p.* from T t join products p on t.product_id=p.id ";
    
    try {
            PreparedStatement st = connection.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Product> getBestPrice() {
        List<Product> list = new ArrayList<>();
        String sql = " select top 6 * from products \n" +
"order by outprice ASC";
    
    try {
            PreparedStatement st = connection.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Product> getLatest() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 6 * from products \n" +
"order by id DESC";
    
    try {
            PreparedStatement st = connection.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    
    public List<Product> getAlltype(int type1,double min,double max,int sort) {
      List<Product> list = new ArrayList<>();
      String sql="";
        if (sort == 1) {
             sql = "select * from [products] where type=? and outprice> ? and outprice< ? order by id ASC";
        }
        if (sort == 2) {
            sql = "select * from [products] where type=? and outprice> ? and outprice< ? order by id DESC";
        }
        if (sort == 3) {
            sql = "select * from [products] where type=? and outprice> ? and outprice< ? order by outprice ASC";
        }
        if (sort == 4) {
            sql = "select * from [products] where type=? and outprice> ? and outprice< ? order by outprice DESC ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, type1);
            st.setDouble(2, min);
            st.setDouble(3, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;  
    }
    public List<Product> getAllbrand(int brand1,double min,double max,int sort) {
      List<Product> list = new ArrayList<>();
       String sql="";
        if (sort == 1) {
             sql = "select * from [products] where brand=? and outprice> ? and outprice< ? order by id ASC";
        }
        if (sort == 2) {
            sql = "select * from [products] where brand=? and outprice> ? and outprice< ? order by id DESC";
        }
        if (sort == 3) {
            sql = "select * from [products] where brand=? and outprice> ? and outprice< ? order by outprice ASC";
        }
        if (sort == 4) {
            sql = "select * from [products] where brand=? and outprice> ? and outprice< ? order by outprice DESC ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brand1);
            st.setDouble(2, min);
            st.setDouble(3, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;  
    }
    public List<Product> getAlltype_brand(int brand1,int type1,double min,double max,int sort){
      List<Product> list = new ArrayList<>();
      String sql="";
        if (sort == 1) {
             sql = "select * from [products] where brand= ? and type= ? and outprice> ? and outprice< ? order by id ASC";
        }
        if (sort == 2) {
            sql = "select * from [products] where brand= ? and type= ? and outprice> ? and outprice< ? order by id DESC";
        }
        if (sort == 3) {
            sql = "select * from [products] where brand= ? and type= ? and outprice> ? and outprice< ? order by outprice ASC";
        }
        if (sort == 4) {
            sql = "select * from [products] where brand= ? and type= ? and outprice> ? and outprice< ? order by outprice DESC ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brand1);
            st.setInt(2, type1);
            st.setDouble(3, min);
            st.setDouble(4, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setInPrice(rs.getDouble("inprice"));
                product.setOutPrice(rs.getDouble("outprice"));
                product.setStock(rs.getInt("stock"));
                Type type = getPType(rs.getInt("id"));
                product.setType(type);
                Brand brand = getPBrand(rs.getInt("id"));
                product.setBrand(brand);
                product.setNokc(rs.getInt("nokc"));
                product.setMaterial(rs.getString("material"));
                product.setSuitability(rs.getString("suitability"));
                product.setDesity(rs.getFloat("density"));
                product.setDiscount(rs.getFloat("discount"));
                List<String> images = getImagesListForProduct(product.getId());
                product.setImg(images);
                List<Review> reviews = getReviewsListForProduct(product.getId());
                product.setReviews(reviews);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;  
    }
    
     public boolean updateStock(int id,int quantity) {
        String sql = "UPDATE products set stock = ? " +
                "where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
     
     public int CountProduct(){
         int total= 0;
         String sql = "select count(*) as total from products ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
             total = rs.getInt("total");   
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
     }
          public int CountLowProduct(){
         int low= 0;
         String sql = "select count(*) as low from products where stock <= 5";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            low = rs.getInt("low");   
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return low;
     }
     public double Totalin(){
         double total = 0;
         String sql = "select sum(p.inprice*od.amount) as total from products p join order_details od on od.product_id= p.id";
         try{
           PreparedStatement st = connection.prepareStatement(sql);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            total = rs.getDouble("total");   
            }  
         }catch (Exception e) {
            System.out.println(e);
        }
         return total;
     }
     
     public double Totalout(){
         double total = 0;
         String sql = "select sum(total_price) as total from orders";
         try{
           PreparedStatement st = connection.prepareStatement(sql);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            total = rs.getDouble("total");   
            }  
         }catch (Exception e) {
            System.out.println(e);
        }
         return total;
     }
     
     public void ProductDelete(int id){
         String sql1 ="delete from product_imgs where product_id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, id);

            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }  
     String sql2 ="delete from reviews where product_id=?";
        try{
            PreparedStatement st = connection.prepareStatement(sql2);
            st.setInt(1, id);

            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            } 
         
         String sql3 ="delete from products where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql3);
            st.setInt(1, id);

            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }  
     }
     
     public void ProductImgDelete(int id, String href){
     String sql1 ="delete from product_imgs where product_id = ? and href = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, id);
            st.setString(2, href);
            st.executeUpdate();
            }catch(SQLException e){
                System.out.println(e);
            }  
}
}