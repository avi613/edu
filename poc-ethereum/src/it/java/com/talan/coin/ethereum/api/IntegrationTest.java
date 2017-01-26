package com.talan.coin.ethereum.api;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class IntegrationTest {
    private Process ethereumProcess;

    @Before
    public void setUp() throws InterruptedException, IOException {
        try {
            cleanEthereumData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            startEthereum();
        }
    }

    @After
    public void tearDown() throws InterruptedException {
        stopEthereum();
    }

    private void startEthereum() throws IOException, InterruptedException {
        ethereumProcess = Runtime.getRuntime().exec(".\\initiate_node.bat");
        int i = 0;
        while (i<5) {
            i++;
            System.out.println("wait while starting ethereum process...");
            Thread.sleep(1000);
        }
        System.out.println("Ethereum process successfully started");
    }

    private void stopEthereum() throws InterruptedException {
//        ethereumProcess.destroy();
        ethereumProcess.destroyForcibly();
        int i = 0;
        while (ethereumProcess.isAlive()) {
            i++;
            System.out.println("wait while stopping ethereum process...");
            Thread.sleep(1000);
        }
        System.out.println("Ethereum process successfully stopped");
    }

    private void cleanEthereumData() throws IOException {
        Path ethereumData = Paths.get("src/it/node/data/");

        Files.walkFileTree(ethereumData, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("delete file: " + file.toString());
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc == null) {
                    Files.delete(dir);
                    System.out.println("delete dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                } else {
                    // directory iteration failed
                    throw exc;
                }
            }
        });
        System.out.println("Ethereum data directory successfully cleaned");
    }
}
