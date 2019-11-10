package controller.utils;

import static controller.utils.StringReplaceUtil.replace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.HostData;

public class HostDataSaver {
	static final Logger LOG = LoggerFactory.getLogger(HostDataSaver.class);
	private static final String FILE_NAME = "hosts.properties";
	private static final String BX_KEY = "host.bx.1.address";
	private static final String CX1_KEY = "host.cx.1.address";
	private static final String CX2_KEY = "host.cx.2.address";
	private static final String FUJI_THIRD_OCTET = ".181.";
	private static final String IRMC_THIRD_OCTET = ".201.";
	private static final String BX_IRMC_KEY = "host.irmc.bx1.address";
	private static final String CX1_IRMC_KEY = "host.irmc.cx1.address";
	private static final String CX2_IRMC_KEY = "host.irmc.cx2.address";

	public void save(HostData hostData, String userPath) {
		Path path = Paths.get(userPath, FILE_NAME);
		try (Stream<String> lines = Files.lines(path)) {
			List<String> replaced = lines.map(line -> replace(line, BX_KEY, hostData.getBx()))
					.map(line -> replace(line, CX1_KEY, hostData.getCx1()))
					.map(line -> replace(line, CX2_KEY, hostData.getCx2()))
					.map(line -> replace(line, BX_IRMC_KEY, changeIpToIrmcAddress(hostData.getBx())))
					.map(line -> replace(line, CX1_IRMC_KEY, changeIpToIrmcAddress(hostData.getCx1())))
					.map(line -> replace(line, CX2_IRMC_KEY, changeIpToIrmcAddress(hostData.getCx2())))
					.collect(Collectors.toList());
			Files.write(path, replaced);
		} catch (IOException e) {
			LOG.error(String.format("Failed to save %s.", FILE_NAME), e);
		}
	}

	private String changeIpToIrmcAddress(String ip) {
		return ip.replace(FUJI_THIRD_OCTET, IRMC_THIRD_OCTET);
	}
}