export function getDateString(date) {
  return new Date(date).toLocaleDateString("lt", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    hour12: false,
  });
}
