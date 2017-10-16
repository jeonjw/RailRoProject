package kr.ac.ajou.railroproject;

/**
 * Created by admin on 2017-08-12.
 */

public class ArticleListItemData {

    int articleId;

    String articleCategory;
    String articleTitle;
    int articleCommentsCount;
    String articleData;
    int articleHitsCount;
    int articleGood;
    int articleBad;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public int getArticleCommentsCount() {
        return articleCommentsCount;
    }

    public void setArticleCommentsCount(int articleCommentsCount) {
        this.articleCommentsCount = articleCommentsCount;
    }

    public String getArticleData() {
        return articleData;
    }

    public void setArticleData(String articleData) {
        this.articleData = articleData;
    }

    public int getArticleHitsCount() {
        return articleHitsCount;
    }

    public void setArticleHitsCount(int articleHitsCount) {
        this.articleHitsCount = articleHitsCount;
    }

    public int getArticleGood() {
        return articleGood;
    }

    public void setArticleGood(int articleGood) {
        this.articleGood = articleGood;
    }

    public int getArticleBad() {
        return articleBad;
    }

    public void setArticleBad(int articleBad) {
        this.articleBad = articleBad;
    }
}
