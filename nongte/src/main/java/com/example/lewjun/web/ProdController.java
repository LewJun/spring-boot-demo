package com.example.lewjun.web;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.Region;
import com.example.lewjun.mapper.RegionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/prod")
public class ProdController {

    @Autowired
    private RegionMapper regionMapper;

    @GetMapping("/province/{code}/{name}")
    public String province(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        log.info("【province code {}】", code);

        model.addAttribute("regionTitle", name);

        model.addAttribute("path", "city");

        final List<Region> cityRegions = regionMapper.queryCitiesByProvinceCode(510000);
        log.info("【cityRegions: {}】", cityRegions);

        model.addAttribute("regions", regionMapper.queryCitiesByProvinceCode(code));

        model.addAttribute("prods", Arrays.asList(
                new Product(123, "金针菇", "kittens.jpg"),
                new Product(124, "芝麻", "kittens.jpg"),
                new Product(124, "芝麻2", "kittens.jpg"),
                new Product(124, "芝麻3", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg")
        ));
        return "prod/prod.html";
    }

    @GetMapping("/city/{code}/{name}")
    public String city(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        log.info("【city code {}】", code);

        model.addAttribute("regionTitle", name);

        model.addAttribute("path", "area");

        model.addAttribute("regions", regionMapper.queryAreasByCityCode(code));
        return "prod/prod.html";
    }

    @GetMapping("/area/{code}/{name}")
    public String area(@PathVariable final String code, @PathVariable final String name, final Model model) {
        log.info("【area code {}】", code);

        model.addAttribute("regionTitle", name);

        return "prod/prod.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable final String id, final Model model) {
        model.addAttribute("title", "芝麻详情");

        model.addAttribute("html", "<blockquote>Web 富文本编辑器有很多，但我们是认真的！</blockquote>\n" +
                "    <h1 style=\"text-align: center\">为何选择 wangEditor</h1>\n" +
                "    <ul>\n" +
                "      <li>\n" +
                "        万星项目<a href=\"https://github.com/wangeditor-team/wangEditor/releases\"\n" +
                "          >Github Star 1w+</a\n" +
                "        >\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        简洁、轻量级、<a href=\"http://www.wangeditor.com/doc/\">文档</a>齐全\n" +
                "      </li>\n" +
                "      <li>QQ 群及时答疑</li>\n" +
                "      <li>\n" +
                "        <a\n" +
                "          href=\"http://www.wangeditor.com/doc/#%E5%BC%80%E5%8F%91%E4%BA%BA%E5%91%98\"\n" +
                "          >开源团队</a\n" +
                "        >维护，非个人单兵作战\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "    <h1>初见</h1>\n" +
                "    <p>\n" +
                "      npm 安装<code>npm i wangeditor --save</code>，几行代码即可创建一个编辑器。\n" +
                "    </p>\n" +
                "    <pre><code>import E from 'wangeditor'\n" +
                "const editor = new E('#div1')\n" +
                "editor.create()</code></pre>\n" +
                "    <p>\n" +
                "      更多使用配置，请阅读<a href=\"http://www.wangeditor.com/doc/\">使用文档</a\n" +
                "      >。\n" +
                "    </p>\n" +
                "    <h1>浏览器兼容性</h1>\n" +
                "    <ul>\n" +
                "      <li>兼容主流 PC 浏览器，IE11+</li>\n" +
                "      <li>不支持移动端和 ipad</li>\n" +
                "    </ul>\n" +
                "    <h1>遇到问题</h1>\n" +
                "    <ul>\n" +
                "      <li>\n" +
                "        加入 QQ 群：<b>901247714</b>，164999061(人已满)，710646022(人已满)\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <a\n" +
                "          href=\"https://github.com/wangeditor-team/wangEditor/issues\"\n" +
                "          target=\"_blank\"\n" +
                "          >提交问题和建议</a\n" +
                "        >\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "    <h1>贡献代码</h1>\n" +
                "    <p>\n" +
                "      欢迎非团队成员贡献代码，提交 Pull Request，请一定参考<a\n" +
                "        href=\"https://github.com/wangeditor-team/wangEditor/blob/master/docs/contribution.md\"\n" +
                "        target=\"_blank\"\n" +
                "        >贡献代码流程</a\n" +
                "      >。\n" +
                "    </p>\n" +
                "    <h1>谁在维护</h1>\n" +
                "    <p>\n" +
                "      wangEditor 现有一个开源团队在维护，团队可以保证答疑、bug 修复和迭代效率。\n" +
                "    </p>\n" +
                "    <p>\n" +
                "      <a\n" +
                "        href=\"http://www.wangeditor.com/doc/#%E5%BC%80%E5%8F%91%E4%BA%BA%E5%91%98\"\n" +
                "        >查看开发团队，或想加入开发团队</a\n" +
                "      >\n" +
                "    </p>\n" +
                "    <h1>为我们点赞</h1>\n" +
                "    <p>如果你感觉有收获，欢迎给我打赏，以激励我们更多输出优质开源内容。</p>\n" +
                "    <p>\n" +
                "      <img src=\"http://www.wangeditor.com/imgs/ali-pay.jpeg\" /><img\n" +
                "        src=\"http://www.wangeditor.com/imgs/wechat-pay.jpeg\"\n" +
                "      />\n" +
                "    </p>\n" +
                "    <p><br /><br /></p>");

        return "prod/detail.html";
    }

    @GetMapping("/list")
    public String list() {
        return "prod/list.html";
    }

    @GetMapping("/list/query")
    @ResponseBody
    public String listQuery() {
        // 需要返回total和rows
        return "{\n" +
                "  \"rows\": [\n" +
                "    {\n" +
                "      \"desc\": \"产品描述\",\n" +
                "      \"id\": 123,\n" +
                "      \"status\": 1,\n" +
                "      \"title\": \"产品名称xxx\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"desc\": \"产品描述23\",\n" +
                "      \"id\": 1233,\n" +
                "      \"status\": 2,\n" +
                "      \"title\": \"产品名称xxserewxx\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"total\": 34\n" +
                "}";
    }

    @GetMapping("/create")
    public String create() {
        return "prod/edit.html";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        final Product product = new Product();
        product.setId(123);
        product.setTitle("标题在这里");
        product.setDesc("描述信息");
        product.setProvince("210000");
        product.setCity("211000");
        product.setArea("211081");
        product.setLevel(2);
        product.setHtml("<p>abc</p>");
        product.setPicUrl("2020/11/f1db2952-4d97-42b2-8ad7-1c0a69595e42.jpg");
        model.addAttribute("prod", product);
        return "prod/edit.html";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(final Product product) {
        log.info("【product {}】", product);
        return "ok";
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(final int id, final int status) {
        log.info("id {}", id);
        log.info("status {}", status);

        return "ok";
    }
}
