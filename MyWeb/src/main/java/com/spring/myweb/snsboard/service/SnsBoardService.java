package com.spring.myweb.snsboard.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.snsboard.dto.SnsBoardRequestDTO;
import com.spring.myweb.snsboard.dto.SnsBoardResponseDTO;
import com.spring.myweb.snsboard.entity.SnsBoard;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SnsBoardService {

	private final ISnsBoardMapper mapper;

	public void insert(SnsBoardRequestDTO dto) {
		
		
		//날짜별로 폴더를 생성해서 관리할 예정입니다.(yyyyMMdd)
		LocalDateTime nw = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String fileLoca = nw.format(dtf);

        //기본 경로는 C:/test/upload로 사용 하겠습니다.
        String uploadPath = "C:/test/upload/";
        
        //폴더가 존재하지 않다면 새롭게 생성해 주시라~
        File Folder = new File(uploadPath + fileLoca);
        if(!Folder.exists()) {
        	System.out.println("지정경로/오늘날짜 폴더가 없어서 폴더 하나 생성.");
        	Folder.mkdirs();
        }
        
        //UUID가 제공하는 랜덤 문자열에 -을 제거해서 전부 사용하시면 됩니다.
        String fileRealName = dto.getFile().getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String uuids = uuid.toString().replace("-","");
        
        //확장자 추출
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

        
        //실제 전달된 파일을 지정한 로컬 경로에 전송(transferTo) 하세요.
        String fileName = uuids + fileExtension;
        File saveFile = new File(uploadPath+ fileLoca +"/"+ fileName );
        try {
			dto.getFile().transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

        
        
        //DB에 각각의 값을 저장하세요. (INSERT)
        //uploadPath -> "C:/test/upload"
        //fileLoca -> 날짜로 된 폴더명
        //fileName -> 랜덤 파일명
        //fileRealName -> 실제 파일명
        mapper.insert(SnsBoard.builder()
        		.writer(dto.getWriter())
        		.uploadPath(uploadPath)
        		.fileLoca(fileLoca)
        		.fileName(fileName)
        		.fileRealName(fileRealName)
        		.content(dto.getContent())
        		.build());
		
		
	}

	public List<SnsBoardResponseDTO> getList(int page) {
		
		List<SnsBoard> list = mapper.getList(Page.builder()
				.pageNo(page)
				.amount(3)
				.build());
		
		List<SnsBoardResponseDTO> dtoList = new ArrayList<>();
		for(SnsBoard board: list) {
			dtoList.add(new SnsBoardResponseDTO(board));
		}
		return dtoList;
	}


	public SnsBoardResponseDTO getDetail(int bno) {
		return new SnsBoardResponseDTO(mapper.getDetail(bno));
	}

	public void delete(int bno) {
			mapper.delete(bno);
		
	}

	public String searchLike(Map<String, String> params) {

		if(mapper.searchLike(params) == 0) {
			mapper.createLike(params);
			return "like";
		} else {
			mapper.deleteLike(params);
			log.info("지나가요~");
			return "delete";
		}
		
	}
	
	
	
	
	
}



