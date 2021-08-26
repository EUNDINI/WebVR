package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artwork;
import model.Exhibition;

public class ArtworkDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ArtworkDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Artwork ����
	public int create(Artwork artwork, Exhibition exhibition) throws SQLException {
		String sql = "INSERT INTO artwork VALUES (?, ?, ?, ?, ?, ?, 0, 0)";		
		Object[] param = new Object[] { exhibition.getId(), 
						artwork.getTitle(),
						artwork.getArtworkAddress(),
						artwork.getDescription(),
						artwork.getArtistName(),
						artwork.getDate() };	
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = { "id" };
		try {
			jdbcUtil.executeUpdate(key); // insert �� ����
			ResultSet rs = jdbcUtil.getGeneratedKeys();

			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				return generatedKey;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}

		return 0;			
	}
	
	// Artwork ����
	public int update(Artwork artwork) throws SQLException {
		String sql = "UPDATE artwork "
					+ "SET title=?, artwork_address=?, description=?, artist_name=? date=? "
					+ "WHERE id=? ";
		Object[] param = new Object[] { artwork.getTitle(),
				artwork.getArtworkAddress(),
				artwork.getDescription(),
				artwork.getArtistName(),
				artwork.getDate() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	// Artwork ����
	public int remove(int id) throws SQLException {
		String sql = "DELETE FROM artwork WHERE id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	// �־��� id�� �ش��ϴ� artwork�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public Artwork findArtwork(int id) throws SQLException {
		String sql = "SELECT artworkId, exhibitionId, title, artworkAddress, description, artistName, date, viewCount, likesCount "
    			+ "FROM artwork "
    			+ "WHERE artworkId=? ";         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			Artwork artwork = null;
			if (rs.next()) {						//  ���� �߰�
				artwork = new Artwork(		// Artwork ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
						rs.getInt("artworkId"),
						rs.getInt("exhibitionId"),
						rs.getString("title"),
						rs.getInt("artworkAddress"),
						rs.getString("description"),
						rs.getString("artistName"),
						rs.getDate("date"),
						rs.getInt("viewCount"),
						rs.getInt("likesCount"));
			}
			return artwork;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	// ��ü Artwork�� List�� ���� �� ��ȯ
	public List<Artwork> findArtworkList() throws SQLException {
		String sql = "SELECT artworkId, exhibitionId, title, artworkAddress, description, artistName, date, viewCount, likesCount "
				+ "FROM artwork "
				+ "ORDER BY artworkId DESC "; 
		
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Artwork> artworkList = new ArrayList<Artwork>();	
			while (rs.next()) {
				Artwork artwork = new Artwork(		
						rs.getInt("artworkId"),
						rs.getInt("exhibitionId"),
						rs.getString("title"),
						rs.getInt("artworkAddress"),
						rs.getString("description"),
						rs.getString("artistName"),
						rs.getDate("date"),
						rs.getInt("viewCount"),
						rs.getInt("likesCount"));
				artworkList.add(artwork);
			}		
			return artworkList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// Artwork�� view(��ȸ��) ����
	public int updateView(Artwork artwork) throws SQLException {
		String sql = "UPDATE artwork "
					+ "SET views_count=? "
					+ "WHERE id=? ";
		Object[] param = new Object[] {artwork.getViewCount() + 1, artwork.getArtworkId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	// Artwork�� like(���ƿ� ��) ����
	public int increaseLike(Artwork artwork) throws SQLException {
		String sql = "UPDATE artwork "
					+ "SET likes_count=? "
					+ "WHERE id=? ";
		Object[] param = new Object[] {artwork.getLikesCount() + 1, artwork.getArtworkId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	// Artwork�� like(���ƿ� ��) ����
	public int decreaseLike(Artwork artwork) throws SQLException {
		String sql = "UPDATE artwork "
					+ "SET likes_count=? "
					+ "WHERE id=? ";
		Object[] param = new Object[] {artwork.getLikesCount() - 1, artwork.getArtworkId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
}