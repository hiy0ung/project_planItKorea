package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.board.Response.BoardResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.entity.Board;
import org.koreait.planitkorea.entity.BoardType;
import org.koreait.planitkorea.repository.BoardRepository;
import org.koreait.planitkorea.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public ResponseDto<List<BoardResponseDto>> getBoardByBoardType(BoardType boardType) {
        List<BoardResponseDto> data = null;
        try {
            List<Board> boards = boardRepository.findByBoardType(boardType);

            data = boards.stream().map(
                    board -> new BoardResponseDto(
                            board.getId(),
                            board.getBoardType(),
                            board.getBoardTitle(),
                            board.getBoardContent(),
                            board.getAuthor(),
                            board.getUploadDate()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
