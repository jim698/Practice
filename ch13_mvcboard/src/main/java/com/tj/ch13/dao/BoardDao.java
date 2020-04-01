package com.tj.ch13.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ch13.dto.BoardDto;

public class BoardDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	private BoardDao() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	// 글목록
	public ArrayList<BoardDto> list(int startRow, int endRow) {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MVC_BOARD ORDER BY bGROUP DESC, BSTEP) A) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Date bdate = rs.getDate("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				String bip = rs.getString("bip");

				dtos.add(new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent, bip));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return dtos;
	}

	// 글 갯수
	public int getBoardTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM MVC_BOARD";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totCnt = rs.getInt(1);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return totCnt;
	}

	// 원글쓰기
	public int write(BoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MVC_BOARD (bID, bNAME, bTITLE, bCONTENT, bGROUP, bSTEP, bINDENT, bIP) VALUES"
				+ "(MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, MVC_BOARD_SEQ.CURRVAL, 0, 0, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBname());
			pstmt.setString(2, dto.getBtitle());
			pstmt.setString(3, dto.getBcontent());
			pstmt.setString(4, dto.getBip());

			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글쓰기 성공" : "글쓰기 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	// 히트업하기
	public void hitUp(int bid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MVC_BOARD SET bHIT = bHIT + 1 WHERE bID = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	// 상세글 보기(dto 가져오기)
	public BoardDto contentView(int bid) {

		hitUp(bid);
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MVC_BOARD WHERE bID = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Date bdate = rs.getDate("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				String bip = rs.getString("bip");

				dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent, bip);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return dto;
	}

	// bid로 dto 가져오기(답글쓰기 + 수정)
	public BoardDto modifyView_replyView(int bid) {

		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MVC_BOARD WHERE bID = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Date bdate = rs.getDate("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				String bip = rs.getString("bip");

				dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent, bip);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return dto;
	}

	// 글 수정하기
	public int modify(BoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MVC_BOARD SET bNAME = ?, bTITLE=?, bCONTENT=?, bIP = ? WHERE bID = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBname());
			pstmt.setString(2, dto.getBtitle());
			pstmt.setString(3, dto.getBcontent());
			pstmt.setString(4, dto.getBip());
			pstmt.setInt(5, dto.getBid());

			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공" : "글수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	// 글 삭제
	public int delete(int bid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM MVC_BOARD WHERE bID = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			result = pstmt.executeUpdate();
			System.out.println(result > FAIL ? bid + "번 글 삭제 성공" : bid + "번글 삭제 실패");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return result;
	}

	// 답변글 전 step A
	private void preReplyStepA(int bgroup, int bstep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MVC_BOARD SET bSTEP = bSTEP + 1 WHERE bGROUP = ? AND bSTEP>?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, bstep);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	// 답글쓰기
	public int reply(BoardDto dto) {

		preReplyStepA(dto.getBgroup(), dto.getBstep());

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MVC_BOARD (bID, bNAME, bTITLE, bCONTENT, bGROUP, bSTEP, bINDENT, bIP) VALUES"
				+ "(MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBname());
			pstmt.setString(2, dto.getBtitle());
			pstmt.setString(3, dto.getBcontent());
			pstmt.setInt(4, dto.getBgroup());
			pstmt.setInt(5, dto.getBstep() + 1);
			pstmt.setInt(6, dto.getBindent() + 1);
			pstmt.setString(7, dto.getBip());

			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "답글쓰기 성공" : "답글쓰기 실패");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}
}
