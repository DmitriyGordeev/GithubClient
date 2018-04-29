package com.example.app.app;

class Committer {
    private String name;
    private String date;

    public Committer(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
}

class CommitInner {
    private String message;
    private Committer committer;

    public CommitInner(String message, Committer committer) {
        this.message = message;
        this.committer = committer;
    }

    public String getMessage() { return message; }
    public Committer getCommiter() { return committer; }
}


public class Commit {

    private String sha;
    private CommitInner commit;

    public Commit(String sha, CommitInner commit) {
        this.sha = sha;
        this.commit = commit;
    }

    public String getSha() {
        return sha;
    }
    public CommitInner getCommit() { return commit; }

}
