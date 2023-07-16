import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    Connection conn = null;
    public ProductDB(){
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectdb","postgres","1998");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(Product p) {
        String querry = "insert into product(name,type,place,warranty)values(?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(querry);
            st.setString(1,p.getName());
            st.setString(2,p.getType());
            st.setString(3,p.getPlace());
            st.setInt(4,p.getWarranty());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String querry = "select name,type,place,warranty from product";
        try {
            PreparedStatement st = conn.prepareStatement(querry);

            ResultSet rs =st.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setName(rs.getString(1));
                p.setType(rs.getString(2));
                p.setPlace(rs.getString(3));
                p.setWarranty(rs.getInt(4));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
