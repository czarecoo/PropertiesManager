package controller.utils.writers;

import static controller.utils.writers.StringReplaceUtil.replace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.holders.HostData;

public class HostDataSaver {
	static final Logger LOG = LoggerFactory.getLogger(HostDataSaver.class);
	private static final String FILE_PATH = "hosts.properties";
	private static final String BX_KEY = "host.bx.1.address";
	private static final String CX1_KEY = "host.cx.1.address";
	private static final String CX2_KEY = "host.cx.2.address";
	private static final String BX_IRMC_KEY = "host.irmc.bx1.address";
	private static final String CX1_IRMC_KEY = "host.irmc.cx1.address";
	private static final String CX2_IRMC_KEY = "host.irmc.cx2.address";

	public void save(HostData hostData, String userPath) {
		Path path = Paths.get(userPath, FILE_PATH);
		try (Stream<String> lines = Files.lines(path)) {
			List<String> replaced = lines.map(line -> replace(line, BX_KEY, hostData.getBx().getIp()))
					.map(line -> replace(line, CX1_KEY, hostData.getCx1().getIp()))
					.map(line -> replace(line, CX2_KEY, hostData.getCx2().getIp()))
					.map(line -> replace(line, BX_IRMC_KEY, hostData.getBx().getIrmc()))
					.map(line -> replace(line, CX1_IRMC_KEY, hostData.getCx1().getIrmc()))
					.map(line -> replace(line, CX2_IRMC_KEY, hostData.getCx2().getIrmc()))
					.collect(Collectors.toList());
			Files.write(path, replaced);
			LOG.info("Saved host data to file: {}", FILE_PATH);
		} catch (IOException e) {
			LOG.error(String.format("Failed to save %s.", FILE_PATH), e);
		}
	}
}