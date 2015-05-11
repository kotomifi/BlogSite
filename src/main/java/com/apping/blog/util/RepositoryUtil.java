package com.apping.blog.util;

import com.apping.blog.Config;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

import java.io.File;

/**
 * Created by baisu on 15-4-15.
 */
public class RepositoryUtil {

    private Repository localRepo;
    private Git git;

    public void initRepository() {
        try {
            Git.cloneRepository().setURI(Config.REMOTE_PATH)
                    .setDirectory(new File(Config.LOCAL_PATH)).call();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void pullRepository() {
        try {
            localRepo = new FileRepository(Config.LOCAL_PATH + "/.git");
            git = new Git(localRepo);
            git.pull().call();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
