[![Software License](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square)](LICENSE.txt)
[![Stories in Ready](https://badge.waffle.io/yu1ro/mahjong4j.png?label=ready&title=Ready&style=flat-square)](https://waffle.io/yu1ro/mahjong4j)
[![Build Status](https://img.shields.io/yu1ro/mahjong4j/master.svg?style=flat-square)](https://travis-ci.org/yu1ro/mahjong4j)
[![Codecov branch](https://img.shields.io/codecov/c/github/yu1ro/mahjong4j/master.svg?style=flat-square)](https://codecov.io/github/yu1ro/mahjong4j?branch=master)
[![Coverity Scan](https://img.shields.io/coverity/scan/7164.svg?style=flat-square)](https://scan.coverity.com/projects/yu1ro-mahjong4j)
[![Gitter](https://img.shields.io/gitter/room/yu1ro/mahjong4j.svg?style=flat-square)](https://gitter.im/yu1ro/mahjong4j?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# mahjong4j
日本のルールにおける麻雀の役判定プログラムのJavaラッパーです

Androidで動いて欲しいのでJava7に対応させます

## 現状
手牌のみを見て判定出来る役が判定できます

|判定可能(例)|判定不可能(例)|
|----------:|:---------|
|三暗刻|ツモ|
|平和|ドラ|
|中|自風牌|
|白|場風牌|

## 使い方
wikiをご覧ください

テストもご覧ください

# Contributing
手が出せるOSSを目指します

Pull Request・Issueはいつでもお待ちしております

## プルリクチャンス！
テストを書きましょう！

役判定を実際に試したテストを追加していただくのが一番簡単です

"/src/test/java/org/mahjong4j/SanankoTanyaoToitoihoTest.java"など[複数の役の名前]+'Test'
をコピーして作りなおすのがオススメです。

## Chat
[![Join the chat at https://gitter.im/yu1ro/mahjong4j](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/yu1ro/mahjong4j?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

チャットオープンしました！
どんな質問・雑談・なんでもOKです

## タスク管理
[waffle.io](https://waffle.io/yu1ro/mahjong4j)を利用しています

通常どおりIssueに投げて頂いて構いません

Readyから左の部分はまだ手をつけていないので

プルリクチャンスです

## Issue
Issueは報告・質問・雑談なんでもどうぞ

被るのは恐れずに書いて下さい。

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
英語もしくは日本語でお願いします

作者の母国語は日本語です

将来は国際化も考えているので英語だとうれしいです

### 文字コード
文字コードはutf-8

改行文字はLFのみでお願いします

そうなってなければ修正・指摘していただけると助かります

最近.editorconfigに記載しました

### プロジェクトファイル
IDEのプロジェクトファイル(.projectなど)はコミットに含めないで下さい

.gitignoreはいつでも変更して頂いて構いません。

# 最後に
作者もまだまだ未熟者ですので何かと至らぬ点があるかと存じますが

よろしくお願い致します