package org.koreait.planitkorea.dto.board.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.BoardType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private BoardType boardType;
    private String boardTitle;
    private String boardContent;
    private String author;
    private LocalDateTime uploadDate;
}
