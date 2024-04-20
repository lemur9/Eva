package com.lemur.eva.core.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 文件读取工具类
 */
public class EnvScanner {
	FileInputStream fis = null;
	InputStreamReader isr = null;
	BufferedReader br = null;

	public Map<String, String> getMap() {
		return map;
	}

	private final Map<String, String> map = new TreeMap<String, String>();

	private final Set<String> lines = new LinkedHashSet<String>();

	public EnvScanner() {

	}

	public void open(String file_name) throws IOException {
		this.open(file_name, "UTF-8");
	}

	public void open(String file_name, String charset) throws IOException {
		this.fis = new FileInputStream(file_name);
		this.isr = new InputStreamReader(fis, charset);
		this.br = new BufferedReader(isr);

		try {
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				line = line.trim();
				if ("".equals(line)) {
					continue;
				}

				String key = "",  value = "";

				int idx = line.indexOf("=");

				if (idx == -1) {
					continue;
				}

				key = line.substring(0, idx).trim();
				value = line.substring(idx + 1);
				this.map.put(key, value);
			}

		} finally {
			this.close();
		}
	}

	/**
	 * 读取文件的所有整行信息
	 * @param file_name	全路径文件名
	 * @param charset	字符集
	 * @param off		跳过行数
	 * @throws IOException 读取异常
	 */
	public void load(String file_name, String charset, int off) throws IOException {
		try {
			this.fis = new FileInputStream(file_name);
			this.isr = new InputStreamReader(fis, charset);
			this.br = new BufferedReader(isr);

			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				line = line.trim();
				if ("".equals(line)) {
					continue;
				}

				if (off-- > 0) {
					continue;
				}

				this.lines.add(line);

				int idx = line.indexOf("=");

				if (idx == -1) {
					continue;
				}

				String key = line.substring(0, idx).trim();
				String value = line.substring(idx + 1);
				this.map.put(key, value);
			}
		} finally {
			this.close();
		}

	}

	/**
	 * 读取文件的所有整行信息
	 * @param file_name	全路径文件名
	 * @param charset	字符集
	 * @throws IOException 读取异常
	 */
	public void load(String file_name, String charset) throws IOException {
		this.load(file_name, charset, 0);
	}

	public void close() {
		try {
			if (this.br != null) {
				this.br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (isr != null) {
				isr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (fis != null) {
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String get(String key) {
		return this.map.get(key);
	}

	public Set<String> get_alone_lines() {
		return this.lines;
	}
}
