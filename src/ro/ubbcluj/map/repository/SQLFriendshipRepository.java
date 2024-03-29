package ro.ubbcluj.map.repository;

import ro.ubbcluj.map.domain.Friendship;
import ro.ubbcluj.map.domain.validators.Validator;

import java.time.LocalDate;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SQLFriendshipRepository implements Repository<Long, Friendship> {
    private final String url;
    private final String username;
    private final String password;
    private final Validator<Friendship> validator;

    public SQLFriendshipRepository(Validator<Friendship> validator, String url, String username, String password) {
        this.validator = validator;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Friendship findOne(Long aLong) {
        Friendship friendship = null;
        String sql = "select * from friendships where id = ?";

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, aLong);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            long id = resultSet.getLong("id");
            Long id_user1 = resultSet.getLong("id_user1");
            Long id_user2 = resultSet.getLong("id_user2");
           // Date date = resultSet.getDate("date");


            friendship = new Friendship(id, id_user1, id_user2);
            return friendship;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return friendship;
    }

    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from friendships");
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                Long id_user1 = resultSet.getLong("id_user1");
                Long id_user2 = resultSet.getLong("id_user2");
               // Date date = resultSet.getDate("date");


                Friendship friendship = new Friendship(id, id_user1, id_user2);

                friendships.add(friendship);
            }
            return friendships;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public void save(Friendship entity) {
        String sql = "insert into friendships(id, id_user1, id_user2) values(?, ?, ?)";
        validator.validate(entity);

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, entity.getId().intValue());
            statement.setInt(2, entity.getUserId1().intValue());
            statement.setInt(3, entity.getUserId2().intValue());
  //          statement.setDate(4, entity.getDate());

            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long aLong) {
        String sql = "delete from friendships where id = ?";

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, aLong);
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Long getNumberOf() {
        Long numberOfElem = null;

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select count(id_friendship) from friendships");
            ResultSet resultSet = statement.executeQuery()){

            resultSet.next();
            numberOfElem = resultSet.getLong("count");

            return numberOfElem;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return numberOfElem;
    }
    @Override
    public void update(Friendship entity) {

    }
}