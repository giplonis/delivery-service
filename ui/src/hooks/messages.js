import { useSnackbar } from "notistack";

export default function useMessage() {
  const { enqueueSnackbar } = useSnackbar();

  function displayMessage(message, type) {
    enqueueSnackbar(message, {
      variant: type,
      preventDuplicate: true,
      anchorOrigin: {
        vertical: "bottom",
        horizontal: "center",
      },
    });
  }

  function displayError(errorMessage) {
    displayMessage(errorMessage, "error");
  }

  return { displayError };
}
