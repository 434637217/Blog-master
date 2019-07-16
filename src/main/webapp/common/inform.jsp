<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<aside id="sidebar" class="sidebar">
    <div class="sidebar-inner">
      <section class="site-overview sidebar-panel sidebar-panel-active">
        <div class="site-author motion-element" itemprop="author" itemscope="" itemtype="http://schema.org/Person">
          <img class="site-author-image" itemprop="image" src="${pageContext.request.contextPath}/static/images/wjx.png" alt="HelloWorld">
          <p class="site-author-name" itemprop="name">Tanbo</p>
          <p class="site-description motion-element" itemprop="description">From Sichuan</p>
        </div>
        <nav class="site-state motion-element">
            <div class="site-state-item site-state-posts">
              <a href="${pageContext.request.contextPath }/blog/archive/1">
                <span class="site-state-item-count">${countsallblogs }</span>
                <span class="site-state-item-name">日志</span>
              </a>
            </div>
            <div class="site-state-item site-state-categories">
              <a href="${pageContext.request.contextPath }/blog/category">
                <span class="site-state-item-count">${countsallcategories }</span>
                <span class="site-state-item-name">分类</span>
              </a>
            </div>
            <div class="site-state-item site-state-tags">
              <a href="${pageContext.request.contextPath }/blog/tags">
                <span class="site-state-item-count">${countsalltags }</span>
                <span class="site-state-item-name">标签</span>
              </a>
            </div>
        </nav>
          <!-- <div class="feed-link motion-element">
            <a href="#" rel="alternate">
              <i class="fa fa-rss"></i>
              RSS
            </a>
          </div> -->
                      <div class="links-of-blogroll-title">
              <i class="fa  fa-fw fa-globe"></i>
              友情链接
            </div>
        <div class="links-of-author motion-element">
              <span class="links-of-author-item">
                <a href="https://github.com/" target="_blank" title="GitHub">
                    <i class="fa fa-fw fa-github"></i>
                  GitHub
                </a>
              </span>
              <span class="links-of-author-item">
                <a href="http://weibo.com/" target="_blank" title="weibo">
                    <i class="fa fa-fw fa-weibo"></i>
                  weibo
                </a>
              </span>
              <span class="links-of-author-item">
                <a href="https://blog.csdn.net/Hello___Sunshine" target="_blank" title="CSDN">
                    <i class="fa fa-fw fa-battery-3"></i>
                  CSDN
                </a>
              </span>
        </div>
      </section>
    </div>
  </aside>