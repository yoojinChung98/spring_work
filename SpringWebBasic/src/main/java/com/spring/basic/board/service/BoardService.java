package com.spring.basic.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.basic.board.dto.BoardListResponseDTO;
import com.spring.basic.board.dto.BoardRequestDTO;
import com.spring.basic.board.entity.Board;
import com.spring.basic.board.repository.IboardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final IboardMapper mapper;

	public void insertArticle(String writer, String title, String content) {
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		mapper.insertArticle(board);		
	}

	public List<BoardListResponseDTO> getArticles() {
		List<BoardListResponseDTO> dtoList = new ArrayList<>();
		List<Board> boardList = mapper.getArticles();
		for(Board b : boardList) {
			BoardListResponseDTO dto = new BoardListResponseDTO(b);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public Board retrieve(int boardNo) {
		return mapper.getArticle(boardNo);
		//원래는 dto로 줘야함!
		
	}

	public void updateArticle(BoardRequestDTO dto) {
//		Board board = new Board();
//		board.setBoardNo(dto.getBoardNo());
//		board.setWriter(dto.getWriter());
//		board.setTitle(dto.getTitle());
//		board.setContent(dto.getContent());
//		mapper.updateArticle(board);
		
		mapper.updateArticle(Board.builder()
									.boardNo(dto.getBoardNo())
									.writer(dto.getWriter())
									.title(dto.getTitle())
									.content(dto.getContent())
									.build());
	}

	public void deleteArticle(int boardNo) {
		mapper.deleteArticle(boardNo);
	}
	
	
	
	
}
