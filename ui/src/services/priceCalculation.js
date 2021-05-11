export function getAdditionalPrice(selectedAttributes) {
  var additionalPrice = 0;
  selectedAttributes.forEach((element) => {
    additionalPrice = additionalPrice + element.additionalPrice;
  });
  return additionalPrice;
}
