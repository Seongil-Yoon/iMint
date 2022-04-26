function goodsSearch() {
    const goodsCategoryVal = "all";
    $("#search-text").on("click", function (e) {
        let searchOption = $("select[name='searchOption']").val();
        let keyword = $("input[name='keyword']").val();
        location.href = `/main?goodsCategory=${goodsCategoryVal}&searchOption=${searchOption}&keyword=${keyword}`
    })
}

function main() {

    goodsSearch();
}
main();