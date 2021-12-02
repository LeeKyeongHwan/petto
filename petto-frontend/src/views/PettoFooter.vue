<template>
    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 col-md-6">
            <h6>CUSTOMER CENTER</h6>
            <p class="text-justify"> 
              <h5>TEL:02-123-4567 </h5>
              <i>월~금<br>
                AM 10:00 ~ PM 05:30<br>
                SAT/SUN/HOLIDAY OFF<br>
                
            
              </i>
            
          </div>

          <div class="col-xs-6 col-md-3">
            <h6>PETTO SNS</h6>          
            <ul class="footer-links">                 
              <a href="https://www.facebook.com/" target="_blank"> <img width="50" height="50"  src="@/assets/footer/Facebook.png"></a>
              &nbsp;
              <a href="https://www.instagram.com/?hl=ko" target="_blank"> <img width="50" height="50" src="@/assets/footer/Instagram.png"></a>
              &nbsp;
              <a href="https://www.kakaocorp.com/page/service/service/KakaoTalk?lang=ko" target="_blank"> <img width="50" height="50" src="@/assets/footer/KakaoTalk.png"></a>
              &nbsp;
              <a href="https://twitter.com/?lang=ko"  target="_blank"> <img width="50" height="50" src="@/assets/footer/Twitter.png"></a>
            </ul>
            </div>

          <div class="col-xs-6 col-md-3">
            <h6>Quick Links</h6>
            <ul class="footer-links">             
            <li><a href="/abandonedAnimal/list/page=0">유기동물</a></li>
            <li><a href="/reportBoard">제보</a></li>
            <li><a href="/voluntaryBoard">자원봉사</a></li>
            <li><a href="/map">지도</a></li>
          </ul>
          
          </div>
        </div>
        <hr>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-sm-6 col-xs-12">
            <p class="copyright-text"> 
              <!-- &copy;
              <a href="#"> </a>
               -->     
               

        
            </p>
          </div>

        </div>
      </div>
</footer>
</template>

<style scoped>
.site-footer
{
  background-color:rgb(245, 245, 245);
  padding:45px 0 20px;
  font-size:15px;
  line-height:24px;
  color:#737373;
}
.site-footer hr
{
  border-top-color:#737373;
  opacity:0.5
}
.site-footer hr.small
{
  margin:20px 0
}
.site-footer h6
{
  color:#353434;
  font-size:16px;
  text-transform:uppercase;
  margin-top:5px;
  letter-spacing:2px
}
.site-footer a
{
  color:#737373;
}
.site-footer a:hover
{
  color:#3366cc;
  text-decoration:none;
}
.footer-links
{
  padding-left:0;
  list-style:none;
  margin-right: 20px;
  margin-bottom: 10px;
}
.footer-links li
{
  display:block
}
.footer-links a
{
  color:#737373
}
.footer-links a:active,.footer-links a:focus,.footer-links a:hover
{
  color:#3366cc;
  text-decoration:none;
}
.footer-links.inline li
{
  display:inline-block
}
.site-footer .social-icons
{
  text-align:right
}
.site-footer .social-icons a
{
  width:40px;
  height:40px;
  line-height:40px;
  margin-left:6px;
  margin-right:0;
  border-radius:100%;
  background-color:#33353d
}
.copyright-text
{
  font-size: large;
  color: #ff0000;
  margin:0
}
@media (max-width:991px)
{
  .site-footer [class^=col-]
  {
    margin-bottom:30px
  }
}
@media (max-width:767px)
{
  .site-footer
  {
    padding-bottom:0
  }
  .site-footer .copyright-text,.site-footer .social-icons
  {
    text-align:center
  }
}
.social-icons
{
  padding-left:0;
  margin-bottom:0;
  list-style:none
}
.social-icons li
{
  display:inline-block;
  margin-bottom:4px
}
.social-icons li.title
{
  margin-right:15px;
  text-transform:uppercase;
  color:#96a2b2;
  font-weight:700;
  font-size:13px
}
.social-icons a{
  background-color:#f5f5f5;
  color:#818a91;
  font-size:16px;
  display:inline-block;
  line-height:44px;
  width:44px;
  height:44px;
  text-align:center;
  margin-right:8px;
  border-radius:100%;
  -webkit-transition:all .2s linear;
  -o-transition:all .2s linear;
  transition:all .2s linear
}
.social-icons a:active,.social-icons a:focus,.social-icons a:hover
{
  color:#fff;
  background-color:#29aafe
}
.social-icons.size-sm a
{
  line-height:34px;
  height:34px;
  width:34px;
  font-size:14px
}
.social-icons a.facebook:hover
{
  background-color:#3b5998
}
.social-icons a.twitter:hover
{
  background-color:#00aced
}
.social-icons a.linkedin:hover
{
  background-color:#007bb6
}
.social-icons a.dribbble:hover
{
  background-color:#ea4c89
}
@media (max-width:767px)
{
  .social-icons li.title
  {
    display:block;
    margin-right:0;
    font-weight:600
  }
}

</style>