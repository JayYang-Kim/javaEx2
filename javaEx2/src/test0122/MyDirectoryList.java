package test0122;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

class DirectoryVO {
	private String name;
	private String modified;
	private String pathType;
	private int type;
	private long length;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getPathType() {
		return pathType;
	}
	public void setPathType(String pathType) {
		this.pathType = pathType;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
}

public class MyDirectoryList {
	public static void directoryList(String pathname) throws IOException {
		File file = new File(pathname);
		
		if (!file.exists()) {
			throw new IOException(pathname + "존재하지 않습니다.");
		}
		
		File []list = file.listFiles();
		
		if (list == null) {
			return;
		}
		
		List<DirectoryVO> dirList = new LinkedList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String s;
		
		try {
			for (File f : list) {
				s = sdf.format(new Date(f.lastModified()));
				
				if (f.isFile()) {
					DirectoryVO vo = new DirectoryVO();
					vo.setName(f.getName());
					vo.setModified(s);
					vo.setPathType("파일");
					vo.setType(1);
					vo.setLength(f.length());
					
					dirList.add(vo);
				} else if (f.isDirectory()) {
					DirectoryVO vo = new DirectoryVO();
					vo.setName(f.getName());
					vo.setModified(s);
					vo.setPathType("폴더");
					vo.setType(0);
					
					dirList.add(vo);
				}
			}
			
			Comparator<DirectoryVO> comp = new Comparator<DirectoryVO>() {

				@Override
				public int compare(DirectoryVO o1, DirectoryVO o2) {
					//return o1.getType() - o2.getType();
					if (o1.getType() > o2.getType()) {
						return 1;
					} else if (o1.getType() < o2.getType()) {
						return -1;
					} else {
						return (int)(o1.getType() - o2.getType());
					}
				}
				
			};
			
			Collections.sort(dirList, comp);
			
			System.out.println("이름\t\t수정날짜\t\t유형\t\t크기");
			
			for (DirectoryVO vo : dirList) {
				System.out.print(vo.getName() + "\t\t");
				System.out.print(vo.getModified() + "\t\t");
				System.out.print(vo.getPathType() + "\t\t");
				
				if (vo.getType() == 1) {
					System.out.println();
					System.out.println(vo.getLength());
					System.out.println();
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		try {
			System.out.println("경로?");
			s = br.readLine();
			directoryList(s);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
