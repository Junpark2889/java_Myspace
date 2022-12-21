package SquiRun;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
	private Image image; // 발판 이미지
	
	// 발판 좌표, 너비, 높이
	private int x;
	private int y;
	private int width;
	private int height;
}
