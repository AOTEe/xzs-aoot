<template>
  <div class="reply-header">
    <div class="reply-navigation">
      <ul class="nav-bar">
        <li class="nav-title"><span class="nav-title-text">评论</span><span
          class="total-reply">1115</span></li>
        <li class="nav-sort hot">
          <div class="hot-sort">最热</div>
          <div class="part-symbol"></div>
          <div class="time-sort">最新</div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReplyHeader"
}
</script>

<style>
.reply-navigation{
  margin-bottom: 22px;
}
.reply-header {
  margin-top: 20px;
}
.reply-header .reply-navigation .nav-bar {
  display: flex;
  align-items: center;
  list-style: none;
  margin: 0;
  padding: 0;
}
.reply-header .reply-navigation .nav-bar .nav-title{
  font-size: 24px;
}
.reply-header .reply-navigation .nav-bar .nav-title .nav-title-text {
  font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,Helvetica,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,sans-serif;
}
.reply-header .reply-navigation .nav-bar .nav-title .total-reply{
  font-size: 14px;
}
.reply-header .reply-navigation .nav-bar .nav-sort {
  font-size: 16px;
  display: flex;
  align-items: center;
  color: #9499a0;
}
.reply-header .reply-navigation .nav-bar .nav-sort.hot .hot-sort{
  color: #18191c;
}
.reply-header .reply-navigation .nav-bar .nav-sort .part-symbol {
  height: 11px;
  margin: 0 12px;
  border-left: solid 1px;
}
.reply-header .reply-navigation .nav-bar .nav-sort .time-sort {
  cursor: pointer;
}
.reply-header .reply-navigation .nav-bar{
  display: flex;
  align-items: center;
  list-style: none;
  margin: 0;
  padding: 0;
}
.reply-header .reply-navigation .nav-bar .nav-title .nav-title-text {
  font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,Helvetica,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,sans-serif;
}
.reply-header .reply-navigation .nav-bar .nav-title {
  font-size: 20px;
}
.reply-header .reply-navigation .nav-bar .nav-title .total-reply {
  font-size: 13px;
  margin: 0 36px 0 6px;
  font-weight: 400;
  color: #9499a0;
}
</style>
