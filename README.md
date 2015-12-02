[![Stories in Ready](https://badge.waffle.io/yu1ro/mahjong4j.png?label=ready&title=Ready)](https://waffle.io/yu1ro/mahjong4j)
[![Build Status](https://travis-ci.org/yu1ro/mahjong4j.svg?branch=master)](https://travis-ci.org/yu1ro/mahjong4j)
[![overity Scan Build Status](https://scan.coverity.com/projects/7164/badge.svg)](https://scan.coverity.com/projects/yu1ro-mahjong4j)
<a href="https://scan.coverity.com/projects/yu1ro-mahjong4j">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/7164/badge.svg"/>
</a>
# mahjong4j
日本のルールにおける麻雀の役判定プログラムのJavaラッパーです

## 現状
- 手牌が和了の形かどうかを判定できます
- 清老頭かどうかの判定ができます

## 使い方
### MahjongHands(手牌)クラス
1. MahjongHandsクラスに手牌を入れて下さい
1. MahjongHandsでは手牌が和了の形かどうかを判定したり、
1. 順子や刻子に振り分けます

### Mahjong(麻雀)クラス
1. 先ほどのMahjongHandsのインスタンスを投げます
1. 現状ではcalcYakuman()を実行すると役満かどうかを判定
1. getYakumanList()で合致する役満のEnumを返します。（現在は清老頭のみ）

詳細はsrc/test/java/

の

org.mahjong4j.ChintohtohTest

を見て頂ければ確認頂けると思います

# Contributing
手が出せるOSSを目指します

Pull Request・Issueはいつでもお待ちしております

## プルリクチャンス！
現在簡単にプルリク出せる状態です！！

それぞれの役判定クラスののisMatchメソッドを作成していただけると嬉しいです

清老頭だけは作成したので、参考にして頂ければと思います

最初に作成した際の名残コード(is\[役名\])のメソッドがあるので

そちらも参考にしてください。

org.mahjong4j.yakuにあります

もちろん他の場所を作って頂いても構いません

## タスク管理
[waffle.io](https://waffle.io/yu1ro/mahjong4j)を利用しています

通常どおりIssueに投げて頂いて構いません。

Readyから左の部分はまだ手をつけていないので

プルリクチャンスです。

## Issue
Issueは報告・質問・雑談なんでもどうぞ

読んだら読んだと書き込みます

出来る限り早く対応致します

ハードルが高ければ作者Twitterでも構いません

[@tresener_yu1ro](https://twitter.com/tresener_yu1ro)

## Pull Request
どんなPull Requestでもありがたいです

- Code
- Comment
- fix typo
- テストの追加
- 翻訳(英語以外も)
- その他何でも

## お願い
### 言語
英語もしくは日本語でお願いします。

作者の母国語は日本語です。

将来は国際化も考えているので英語だとうれしいです

### 文字コード
文字コードはutf-8

改行文字はLFのみでお願いします。

そうなってなければ修正・指摘していただけると助かります。

最近.editorconfigに記載しました

### プロジェクトファイル
IDEのプロジェクトファイル(.projectなど)はコミットに含めないで下さい。

.gitignoreはいつでも変更して頂いて構いません。

# 最後に
作者もまだまだ未熟者ですので何かと至らぬ点があるかと存じますが

よろしくお願い致します。