#[path = "../src/utils.rs"] mod utils;

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = utils::add(21, 21);
        assert_eq!(result, 42);
    }
}